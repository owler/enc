package org.simple.enc;

import com.intellij.openapi.project.Project;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;

public class DecoderConfigurableGUI {
    private final JTextField keyField;
    private final JPanel rootPanel;
    private DecoderConfig conf;
    private final JCheckBox statusBox;


    public DecoderConfigurableGUI() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        JLabel label = new JLabel();
        label.setText("Encryption key");
        rootPanel.add(label, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        keyField = new JPasswordField();
        rootPanel.add(keyField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Status Bar");
        rootPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        statusBox = new JCheckBox();
        statusBox.setText("");
        rootPanel.add(statusBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

    }


    public void createUI(Project project) {
        this.conf = DecoderConfig.getInstance(project);
        if (conf != null) {
            this.keyField.setText(conf.getEncryptionKey());
            this.statusBox.setSelected(conf.isStatusBoxEnabled());
        } else {
            this.keyField.setText("conf is null");
            this.statusBox.setSelected(true);
        }
    }

    public JComponent getRootPanel() {
        return rootPanel;
    }

    public void apply() {
        conf.setEncryptionKey(keyField.getText());
        conf.setStatusBoxEnabled(statusBox.isSelected());
    }

    public void reset() {
        keyField.setText(conf.getEncryptionKey());
        statusBox.setSelected(conf.isStatusBoxEnabled());
    }

    public String getKey() {
        return keyField.getText();
    }

    public boolean isStatusEnabled() {
        return statusBox.isSelected();
    }
}
