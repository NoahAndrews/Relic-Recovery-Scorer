package net.frogbots.relicrecoveryscorecalculator.backend.export.csv;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.opencsv.CSVWriter;
import net.frogbots.relicrecoveryscorecalculator.backend.Scores;
import net.frogbots.relicrecoveryscorecalculator.backend.Utils;
import net.frogbots.relicrecoveryscorecalculator.backend.export.ExportBundle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

class CsvCommon
{
    static final String[] columns = {
            "Time",
            "Match",
            "Team",
            "Comment",
            "Jewel",
            "Pre-loaded glyph",
            "[Auto] glyphs scored",
            "Autonomous parking",
            "[Tele-Op] glyphs scored",
            "Cryptobox rows complete",
            "Cryptobox columns complete",
            "Cipher completed",
            "Relic position",
            "Relic upright",
            "Robot balanced",
            "Minor penalties",
            "Major penalties",
            "TOTAL SCORE"
    };

    static void saveToCSV (Context context, File file, String[][] array) throws IOException
    {
        CSVWriter writer = new CSVWriter(new FileWriter(file));
        writer.writeAll(Arrays.asList(array));
        writer.close();
    }

    static void writeScoresToRow (String[][] array, ExportBundle bundle, int rowNumber)
    {
        @SuppressLint("SimpleDateFormat")
        DateFormat df = new SimpleDateFormat("EEE d MMM yyyy HH:mm");

        array[rowNumber][0]  = (df.format(Calendar.getInstance().getTime()));
        array[rowNumber][1]  = bundle.match;
        array[rowNumber][2]  = Integer.toString(bundle.team);
        array[rowNumber][3]  = bundle.comment;
        array[rowNumber][4]  = Utils.jewelForExport(Scores.getAutonomousJewelLevel());
        array[rowNumber][5]  = (Utils.glyphForExport(Scores.getAutonomousPreloadedGlyphLevel()));
        array[rowNumber][6]  = Integer.toString(Scores.getAutonomousGlyphsScored());
        array[rowNumber][7]  = Boolean.toString(Scores.getParkingLevel() > 0);
        array[rowNumber][8]  = Integer.toString(Scores.getTeleOpGlyphsScored());
        array[rowNumber][9]  = Integer.toString(Scores.getTeleopCryptoboxRowsComplete());
        array[rowNumber][10]  = Integer.toString(Scores.getTeleopCryptoboxColumnsComplete());
        array[rowNumber][11] = Boolean.toString(Scores.getTeleopCipherLevel() > 0);
        array[rowNumber][12] = Integer.toString(Scores.getEndgameRelicPosition());
        array[rowNumber][13] = Boolean.toString(Scores.getEndgameRelicOrientation() > 0);
        array[rowNumber][14] = Boolean.toString(Scores.getEndgameRobotBalanced() > 0);
        array[rowNumber][15] = Integer.toString(Scores.getNumMinorPenalties());
        array[rowNumber][16] = Integer.toString(Scores.getNumMajorPenalties());
        array[rowNumber][17] = Integer.toString(Scores.getTotalScore());
    }
}
