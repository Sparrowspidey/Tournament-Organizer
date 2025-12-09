package Schedule;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import Elimination.KnockoutScheduler.MatchNode;

public class ScheduleExporter {

    // Export the tournament schedule to PDF
    public static void exportSchedule(MatchNode root, String startDateStr,
                                      String outputPath, String leagueName, String leagueLocation, String sportName) {
        try {
            // Convert user start date string into LocalDateTime (start 10 AM)
            LocalDateTime startDate = LocalDateTime.parse(startDateStr + "T10:00:00");

            // Extract matches in order
            List<String[]> matches = extractMatches(root);

            // Create PDF document
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            // === Title Section ===
            Font mainTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 22, BaseColor.BLUE);
            Paragraph mainTitle = new Paragraph("Tournament Schedule", mainTitleFont);
            mainTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(mainTitle);
            document.add(Chunk.NEWLINE);

            // League details
            Font subTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.DARK_GRAY);
            Paragraph leagueTitle = new Paragraph("League: " + leagueName, subTitleFont);
            leagueTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(leagueTitle);

            Paragraph leagueLocationPara = new Paragraph("Location: " + leagueLocation, subTitleFont);
            leagueLocationPara.setAlignment(Element.ALIGN_CENTER);
            document.add(leagueLocationPara);
            document.add(Chunk.NEWLINE);

            Paragraph sportPara = new Paragraph("Sport: " + sportName, subTitleFont);
            sportPara.setAlignment(Element.ALIGN_CENTER);
            document.add(sportPara);
            document.add(Chunk.NEWLINE);

            // Timestamp
            Font smallFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY);
            String timestamp = "Schedule generated on: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            Paragraph generatedOn = new Paragraph(timestamp, smallFont);
            generatedOn.setAlignment(Element.ALIGN_RIGHT);
            document.add(generatedOn);
            document.add(Chunk.NEWLINE);

            // === Table Section ===
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 4, 4});

            // Table header
            addTableHeader(table, "Date/Time");
            addTableHeader(table, "Team 1");
            addTableHeader(table, "Team 2");

            // Populate matches with timings (2 matches per day → 10AM, 1PM)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            int matchCount = 0;
            for (String[] match : matches) {
                LocalDateTime matchDateTime = startDate.plusHours((matchCount % 2) * 3)  // 10AM, 1PM
                                                       .plusDays(matchCount / 2);        // 2 per day

                table.addCell(matchDateTime.format(formatter));
                table.addCell(match[0]);
                table.addCell(match[1]);
                matchCount++;
            }

            document.add(table);
            document.close();

            // Make file read-only
            File file = new File(outputPath);
            file.setReadOnly();

            System.out.println("Schedule exported to: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Extract matches in order from knockout tree
    private static List<String[]> extractMatches(MatchNode root) {
        List<String[]> matches = new ArrayList<>();
        traverseMatches(root, matches);
        return matches;
    }

    private static void traverseMatches(MatchNode node, List<String[]> matches) {
        if (node == null) return;

        if (node.getTeam1() != null && node.getTeam2() != null
                && !node.getTeam1().startsWith("Winner")
                && !node.getTeam2().startsWith("Winner")) {
            matches.add(new String[]{node.getTeam1(), node.getTeam2()});
        }

        traverseMatches(node.getLeft(), matches);
        traverseMatches(node.getRight(), matches);
    }

    private static void addTableHeader(PdfPTable table, String header) {
        PdfPCell cell = new PdfPCell(new Phrase(header));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
}
