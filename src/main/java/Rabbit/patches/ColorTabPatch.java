package Rabbit.patches;

import Rabbit.TheRabbit;
import basemod.patches.com.megacrit.cardcrawl.screens.mainMenu.ColorTabBar.ColorTabBarFix;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import javassist.CtBehavior;

import static Rabbit.MainModfile.makeID;

public class ColorTabPatch {
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(makeID(TheRabbit.class.getSimpleName()));

    @SpirePatch2(clz = ColorTabBarFix.Render.class, method = "Insert")
    public static class ChangeName {
        @SpireInsertPatch(locator = Locator.class, localvars = {"playerClass", "tabName"})
        public static void plz(AbstractPlayer.PlayerClass playerClass, @ByRef String[] tabName) {
            if (playerClass == TheRabbit.Enums.THE_RABBIT) {
                tabName[0] = characterStrings.NAMES[2];
            }
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher m = new Matcher.MethodCallMatcher(FontHelper.class, "renderFontCentered");
                return LineFinder.findInOrder(ctBehavior, m);
            }
        }
    }
}
