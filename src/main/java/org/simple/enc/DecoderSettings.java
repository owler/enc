package org.simple.enc;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DecoderSettings implements Configurable {
    private final DecoderConfig conf;
    private final Project project;
    private DecoderConfigurableGUI gui;

    public DecoderSettings(Project project) {
        this.project = project;
        this.conf = DecoderConfig.getInstance(project);
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Decoder";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        gui = new DecoderConfigurableGUI();
        gui.createUI(project);
        return gui.getRootPanel();
    }

    @Override
    public boolean isModified() {
        return !gui.getKey().equals(conf.getEncryptionKey()) || !(gui.isStatusEnabled() == conf.isStatusBoxEnabled());
    }

    @Override
    public void apply() throws ConfigurationException {
        gui.apply();
    }

    @Override
    public void reset() {
        gui.reset();
    }


}
