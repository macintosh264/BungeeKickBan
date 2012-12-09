package dev.xyzcraft.net.bkbw;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class WhitelistDatabase extends MacDatabase{

	@Override
	String name() {
		return "whitelistdb";
	}
	public boolean whitelistOn() {
		super.reload();
		try {
			return super.databaseVar().getBoolean("whitelist");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	public boolean whitelisted(String name) {
		super.reload();
		try {
			return super.databaseVar().getJSONArray("whitelisted").al().contains(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return true;
		}
	}
	public void whitelist(String name) {
		super.reload();
		try {
			super.databaseVar().getJSONArray("whitelisted").put(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.save();
	}
	public void unWhitelist(String name) {
		super.reload();
		try {
			super.databaseVar().getJSONArray("whitelisted").al().remove(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.save();
	}
	@Override
	JSONObject defaultDb() {
		// TODO Auto-generated method stub
		JSONObject defaultDb = new JSONObject();
		try {
			defaultDb.put("whitelist", false);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			defaultDb.put("whitelisted", (new ArrayList<String>()).add("macintosh264"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defaultDb;
	}

}
