package org.sufficientlysecure.keychain.ui.chips;


import java.util.List;

import android.content.Context;
import android.util.AttributeSet;

import com.pchmn.materialchips.ChipsInput;
import com.pchmn.materialchips.adapter.FilterableAdapter.FilterableItem;
import org.sufficientlysecure.keychain.model.SubKey.UnifiedKeyInfo;
import org.sufficientlysecure.keychain.ui.chips.EncryptRecipientChipsInput.EncryptRecipientChip;


public class EncryptRecipientChipsInput extends ChipsInput<EncryptRecipientChip> {
    public EncryptRecipientChipsInput(Context context) {
        super(context);
        init();
    }

    public EncryptRecipientChipsInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        EncryptRecipientChipAdapter chipsAdapter = new EncryptRecipientChipAdapter(getContext(), this);
        setChipsAdapter(chipsAdapter);
    }

    public void setData(List<EncryptRecipientChip> keyInfoChips) {
        EncryptRecipientDropdownAdapter chipDropdownAdapter = new EncryptRecipientDropdownAdapter(getContext(), keyInfoChips);
        setChipDropdownAdapter(chipDropdownAdapter);
    }

    public static class EncryptRecipientChip implements FilterableItem {
        public final UnifiedKeyInfo keyInfo;

        EncryptRecipientChip(UnifiedKeyInfo keyInfo) {
            this.keyInfo = keyInfo;
        }

        @Override
        public long getId() {
            return keyInfo.master_key_id();
        }

        @Override
        public boolean isKeptForConstraint(CharSequence constraint) {
            return keyInfo.uidSearchString().contains(constraint);
        }
    }

    public static EncryptRecipientChip chipFromUnifiedKeyInfo(UnifiedKeyInfo keyInfo) {
        return new EncryptRecipientChip(keyInfo);
    }
}
