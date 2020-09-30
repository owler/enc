package org.simple.enc;

import com.intellij.codeInsight.hint.HintManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.wm.WindowManager;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class DecodeAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        DecoderConfig conf = DecoderConfig.getInstance(e.getRequiredData(CommonDataKeys.PROJECT));


        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        if (caretModel.getCurrentCaret().hasSelection()) {
            String query = caretModel.getCurrentCaret().getSelectedText();
            if (query != null) {
                String res = new Crypto(conf.getEncryptionKey()).decrypt(query);
                if (!query.equals(res)) {
                    StringSelection data = new StringSelection(res);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(data, data);
                    if (conf.isStatusBoxEnabled()) {
                        WindowManager.getInstance().getStatusBar(e.getProject()).setInfo("Decoding: " + res);
                    }
                    HintManager.getInstance().showInformationHint(editor, res);
                }
            }
        }
    }

    /**
     * Only make this action visible when text is selected.
     *
     * @param e
     */
    @Override
    public void update(AnActionEvent e) {
        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        e.getPresentation().setEnabledAndVisible(caretModel.getCurrentCaret().hasSelection() &&
                caretModel.getCurrentCaret().getSelectedText().startsWith("ENC(") &&
                caretModel.getCurrentCaret().getSelectedText().endsWith(")"));
    }
}