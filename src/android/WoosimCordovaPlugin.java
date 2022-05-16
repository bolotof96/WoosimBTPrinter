package cordova-plugin-custom;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import com.woosim.printer.WoosimBarcode;
import com.woosim.printer.WoosimCmd;
import com.woosim.printer.WoosimImage;
import com.woosim.printer.WoosimService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class WoosimCordovaPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        else if (action.equals("PrintText")) {
            String message = args.getString(0);
            this.PrintText(message, callbackContext);
            return true;
        }

        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    public void PrintText(String message, CallbackContext callbackContext) {
        String string = message.toString();
        byte[] text = null;

        if (string == null)
            callbackContext.error("Expected one non-empty string argument.");
        else {
            try {
                text = string.getBytes("US-ASCII");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byteStream.write(WoosimCmd.setTextStyle(mEmphasis, mUnderline, false, mCharsize, mCharsize));
        byteStream.write(WoosimCmd.setTextAlign(mJustification));
        if (text != null) byteStream.write(text);
        byteStream.write(WoosimCmd.printData());

        sendData(WoosimCmd.initPrinter());
        sendData(byteStream.toByteArray());

        callbackContext.success(message);
    }
}
