/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import dao.DAOException;
import dao.PlaceDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import modele.Place;
import modele.Zone;

/**
 *
 * @author arthur
 */
public class TraitementPlaces {

    public static Map<Zone, List<Place>> TraiterPlaces(DataSource ds, String places) throws DAOException {
        int noP, noR, noZ;
        String[] tmp;
        String[] strArray = places.split("!");
        PlaceDAO placeDAO = new PlaceDAO(ds);
        Map<Zone, List<Place>> map = new HashMap<Zone, List<Place>>();
        for (String el : strArray) {
            tmp = el.split("/");
            noP = Integer.parseInt(tmp[0]);
            noR = Integer.parseInt(tmp[1]);
            noZ = Integer.parseInt(tmp[2]);
            Place place = new Place(noP, noR, noZ);
            placeDAO.lire(place);
            List<Place> listPlaces = map.get(place.getZone());
            if (listPlaces == null) {
                listPlaces = new ArrayList<Place>();
                listPlaces.add(place);
                map.put(place.getZone(), listPlaces);
            } else {
                listPlaces.add(place);
            }
        }
        return map;
    }

    public void TraiterPlacesPourBD(Map<Zone, List<Place>> map) {
        
    }
    
    public static float getPrixTotalPlaces(Map<Zone, List<Place>> map){
        float total = 0;
        for (Map.Entry<Zone, List<Place>> entry : map.entrySet()) {
            Zone z = entry.getKey();
            for (Place p : entry.getValue()){
                total += z.getTarifBase();
            }
            
        }
        return total;
    }
    
    public static String prixString(List<Zone> listeZones) {
        float[] prices = new float[4];
        int i = 0;
        for(Zone z:listeZones) {
            prices[i++] = z .getTarifBase();
        }
        Arrays.sort(prices);        
        String prix = prices[0] + "," + prices[1] + "," + prices[2] + "," + prices[3];
        return prix;
    }
}
