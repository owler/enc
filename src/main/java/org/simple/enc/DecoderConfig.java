package org.simple.enc;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * PersistentStateComponent keeps project config values.
 * Similar notion of 'preference' in Android
 */
@State(
        name = "DecoderConfig",
        storages = {
                @Storage("DecoderConfig.xml")}
)
public class DecoderConfig implements PersistentStateComponent<DecoderConfig> {

    private String encryptionKey = "changeit";
    private boolean statusBoxEnabled = true;

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public boolean isStatusBoxEnabled() {
        return statusBoxEnabled;
    }

    public void setStatusBoxEnabled(boolean statusBoxEnabled) {
        this.statusBoxEnabled = statusBoxEnabled;
    }

    @Nullable
    @Override
    public DecoderConfig getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull DecoderConfig state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public static DecoderConfig getInstance(Project project) {
        return project.getService(DecoderConfig.class);
    }
}
