package fer.hr.masterthesis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Preferences {
    public static boolean FONT_CHANGED_SIZE_INCREASE = false;
    public static boolean FONT_CHANGED_SIZE_DECREASE = false;
    public static boolean FONT_FAMILY_CHANGED = false;
    public static boolean CONTRAST_CHANGED = false;
    public static final int FONT_SIZE = 14;
    public static int FONT_DECREASED_COUNT = 0;
    public static int FONT_CHANGED = 0;
    public static int MAX_TEXTVIEW_LENGTH = 50;
    public static int OPTIMAL_TEXTVIEW_LENGTH = 35;
    public static int MAX_SPLITS = 6;
    public static final CharSequence NORMAL_SEARCH ="9412019453891241893241";
    public static final CharSequence CATEGORICAL_SEARCH ="414891859429041204214";
    public static  boolean isCategorical = false;
    public static final List<String> vrstaTeskoce = new ArrayList<>(Arrays.asList(
            "MOTORIČKE",
            "KOGNITIVNE",
            "TEŠKOĆE SLUHA",
            "VIŠESTRUKE",
            "TEŠKOĆE VIDA"
    ));
    public static final List<String> vrstaPrimjene = new ArrayList<>(Arrays.asList(
            "EDUKACIJA",
            "KOMUNIKACIJA",
            "REHABILITACIJA",
            "DRUGE PRIMJNE"
    ));
    public static final List<String> platforma = new ArrayList<>(Arrays.asList(
            "WINDOWS",
            "ANDROID",
            "IOS",
            "TIZEN",
            "LINUX",
            "SPECIFIČNE PLATFORME"
    ));
    public static final List<String> jezik = new ArrayList<>(Arrays.asList(
            "HRVATSKI",
            "ENGLESKI"
    ));
    public static final List<String> cijena = new ArrayList<>(Arrays.asList(
            "BESPLATNO",
            "NIJE BESPLATNO"
    ));
}
