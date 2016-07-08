package georgikoemdzhiev.eurefpet.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import georgikoemdzhiev.eurefpet.R;

/**
 * Created by koemdzhiev on 08/07/16.
 */
public class GovernmentResponseDialog extends DialogFragment {
    private static final String DIALOG_MESSAGE = "message";
    private static String message = "";

    public static GovernmentResponseDialog newInstance(String m){
        GovernmentResponseDialog dialog = new GovernmentResponseDialog();
        Bundle data = new Bundle();
        data.putString(DIALOG_MESSAGE,m);
        dialog.setArguments(data);

        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = getArguments().getString(DIALOG_MESSAGE);
        View v =  LayoutInflater.from(getActivity()).inflate(R.layout.dialog_custom_view,null);

        ((TextView) v.findViewById(R.id.government_response)).setText(message);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Government response");
        dialogBuilder.setView(v);
        dialogBuilder.setPositiveButton("OK",null);

        return dialogBuilder.create();
    }
}
