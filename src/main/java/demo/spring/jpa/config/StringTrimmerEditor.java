package demo.spring.jpa.config;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author ashrafhasan
 * @since 1/24/17
 */
public class StringTrimmerEditor extends PropertyEditorSupport {
    private final String charsToDelete;
    private final boolean emptyAsNull;

    public StringTrimmerEditor(boolean emptyAsNull) {
        this.charsToDelete = null;
        this.emptyAsNull = emptyAsNull;
    }

    public StringTrimmerEditor(String charsToDelete, boolean emptyAsNull) {
        this.charsToDelete = charsToDelete;
        this.emptyAsNull = emptyAsNull;
    }

    public void setAsText(String text) {
        if (text == null) {
            this.setValue((Object) null);
        } else {
            String value = text.trim();
            if (this.charsToDelete != null) {
                value = StringUtils.deleteAny(value, this.charsToDelete);
            }

            if (this.emptyAsNull && "".equals(value)) {
                this.setValue((Object) null);
            } else {
                this.setValue(value);
            }
        }

    }

    public String getAsText() {
        Object value = this.getValue();
        return value != null ? value.toString() : "";
    }
}
