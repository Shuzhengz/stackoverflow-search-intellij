package prj.plugins.stackoverflowsearchintellij;

import com.intellij.DynamicBundle;
import com.intellij.ide.BrowserUtil;
import com.intellij.lang.Language;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.util.Objects;

// Currently following the tutorial from https://www.baeldung.com/intellij-new-custom-plugin

public class StackoverflowSearchIntellij extends AnAction {
    String txtToSearch;

    /**
     * Performs the search action
     * @param e Carries information on the invocation place
     */
    @Override
    public void actionPerformed(AnActionEvent e) {
        Language lang = Objects.requireNonNull(e.getData(CommonDataKeys.PSI_FILE)).getLanguage();
        String languageTag = "+[" + lang.getDisplayName().toLowerCase() + "]";

        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel model = editor.getCaretModel();
        String selectedText = model.getCurrentCaret().getSelectedText();

        try {
            assert selectedText != null;
            txtToSearch = selectedText.replace(' ', '+') + languageTag;
        } catch (Exception txtHandle) {
            System.out.println("Error handling text");
        }

        BrowserUtil.browse("https://stackoverflow.com/search?q=" + txtToSearch);
    }
}
