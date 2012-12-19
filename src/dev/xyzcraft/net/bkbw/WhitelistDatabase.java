package dev.xyzcraft.net.bkbw;

import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

public class WhitelistDatabase extends MacDatabase{

	@Override
	String name() {
		return "whitelistdb";
	}
	public boolean whitelistOn() {
		super.reload();
                boolean wl = false;
                try {
                   wl = super.databaseVar().getBoolean("whitelist");
               } catch (JSONException ex) {
                  Logger.getLogger(WhitelistDatabase.class.getName()).log(Level.SEVERE, null, ex);
               }
		return wl;
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
                if (!super.databaseVar().has("whitelisted")) {
              
                }
		try {
			super.databaseVar().getJSONArray("whitelisted").put(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.save();
	}
        public HashSet<String> list() throws JSONException {
            super.reload();
            JSONArray whitelisted = super.databaseVar().getJSONArray("whitelisted");
            int index = 0;
            HashSet<String> wl;
            wl = new HashSet();
            while (!whitelisted.isNull(index)) {
                wl.add(whitelisted.getString(index));
                index++;
            }
            return wl;
        }
        public void turnOnWhitelist() throws JSONException {
              super.reload();
              super.databaseVar().put("whitelist",true);
              super.save();
        }
        public void turnOffWhitelist() throws JSONException {
              super.reload();
              super.databaseVar().put("whitelist",false);
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
            } catch (JSONException ex) {
                Logger.getLogger(WhitelistDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            Collection<String> users = new HashSet<String>();
            try {
                defaultDb.put("whitelisted", users);
           } catch (JSONException ex) {
                Logger.getLogger(WhitelistDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            return defaultDb;
	}

}
