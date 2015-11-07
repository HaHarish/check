package com.nidarooms.searchresult;

import java.util.ArrayList;

public class PlaceData {

 // public static String[] placeNameArray = {"Bora Bora", "Canada", "Dubai", "Hong Kong", "Iceland", "India", "Kenya", "London", "Switzerland", "Sydney"};

  public static String[] placeNameArray = {"NIDA Rooms One", "NIDA Rooms Two", "NIDA Rooms Three", "NIDA Rooms Four", "NIDA Rooms Five", "NIDA Rooms Six"};

  public static ArrayList<Place> placeList() {
    ArrayList<Place> list = new ArrayList<>();
    for (int i = 0; i < placeNameArray.length; i++) {
      Place place = new Place();
      place.name = placeNameArray[i];
      place.imageName = placeNameArray[i].replaceAll("\\s+", "").toLowerCase();
      if (i == 2 || i == 5) {
        place.isFav = true;
      }
      list.add(place);
    }
    return (list);
  }
}
