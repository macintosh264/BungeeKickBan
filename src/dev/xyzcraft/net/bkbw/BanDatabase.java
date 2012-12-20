package dev.xyzcraft.net.bkbw;

import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class BanDatabase extends MacDatabase {
	@Override
	String name() {
		return "bandb";
	}
	public boolean isBanned(String name) {
		super.reload();
		return super.databaseVar().has(name);
	}
	public void ban(String name, String reason) {
		try {
			super.databaseVar().put(name, reason);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.save();
	}
        public Set<String> bannedUsers() {
            return super.databaseVar().keySet();
        }
	public void unban(String name) {
		super.reload();
		super.databaseVar().remove(name);
		super.save();
	}
	public String banReason(String name) {
		super.reload();
		try {
			return super.databaseVar().getString(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Banned: The Ban Hammer has spoken!";
	}
	@Override
	JSONObject defaultDb() {
		// TODO Auto-generated method stub
		JSONObject defaultDb = new JSONObject();
		return defaultDb;
	}
}
