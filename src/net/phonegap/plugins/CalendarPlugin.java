package net.phonegap.excellentcompany.plugins;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;


public class CalendarPlugin extends CordovaPlugin
{
	public static final String ACTION_ADD_TO_CALENDAR = "addToCalendar";
	public static final Integer RESULT_CODE_CREATE = 0;
	private CallbackContext callback;

	@Override
	public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException
	{
		try
		{
			if (ACTION_ADD_TO_CALENDAR.equals(action))
			{
				final JSONObject arg_object = args.getJSONObject(0);
				callback = callbackContext;
				final Intent calIntent = new Intent(Intent.ACTION_EDIT).setType("vnd.android.cursor.item/event")
						.putExtra("beginTime", arg_object.getLong("startTimeMillis"))
						.putExtra("endTime", arg_object.getLong("endTimeMillis")).putExtra("title", arg_object.getString("title"))
						.putExtra("description", arg_object.getString("description"))
						.putExtra("eventLocation", arg_object.getString("eventLocation"));

				this.cordova.startActivityForResult(this, calIntent, RESULT_CODE_CREATE);
				return true;
			}
		}
		catch (final Exception e)
		{
			System.err.println("Exception: " + e.getMessage());
			return false;
		}

		return false;
	}

	@Override
	public void onActivityResult(final int requestCode, final int resultCode, final Intent data)
	{
		if (requestCode == RESULT_CODE_CREATE)
		{
			if (resultCode == Activity.RESULT_OK)
			{
				callback.success();
			}
			else
			{
				callback.error("Activity result code " + resultCode);
			}
		}
	}
}
