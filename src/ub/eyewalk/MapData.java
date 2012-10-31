package ub.eyewalk;

import java.util.Vector;

public class MapData {

		public Vector<MapObject> objectList;
		
		public MapData() {
			objectList = new Vector<MapObject> ();
			
			// Interior lab items.
		
			// Doors 			
			objectList.add(new MapObject(0, 0, 5, 67, 5, 67, MapObject.DOOR, "Baldy 19. Awesome Lab"));
			objectList.add(new MapObject(-1, 643.75f, 5, 48, 5, 72, MapObject.DOOR, "Baldy 19. Awesome Lab"));
			
			// East (Lab Interior) Wall
			objectList.add(new MapObject(-104f, -36f, 213, 5, 203, 29, MapObject.WALL));
		
			// West (Lab Interior) Wall
			objectList.add(new MapObject(-96.5f, 678.5f, 191, 5, 215, 29, MapObject.WALL));
			
			// North (Lab Interior) Walls
			objectList.add(new MapObject(0, 36.25f, 5, 5.5f, 29, 29.5f, MapObject.WALL));
			objectList.add(new MapObject(24f, 36.25f, 53, 5, 77, 5, MapObject.WALL));
			objectList.add(new MapObject(48, 113.5f, 5, 149f, 29, 159f, MapObject.WALL));
			objectList.add(new MapObject(48, 317f, 5f, 222f, 29, 222f, MapObject.WALL));			
			objectList.add(new MapObject(48, 521.5f, 5f, 151f, 29, 222f, MapObject.WALL));
		    objectList.add(new MapObject(23.5f, 599.5f, 54f, 5f, 78, 29f, MapObject.WALL));
		    objectList.add(new MapObject(23.5f, 665.75f, 54f, 5f, 78, 29f, MapObject.WALL));
		    objectList.add(new MapObject(-1, 673.125f, 5, 10.5f, 29, 34.5f, MapObject.WALL));
		    objectList.add(new MapObject(-1, 608.5f, 5, 22.5f, 29, 46.5f, MapObject.WALL));
			// Pillars
			objectList.add(new MapObject(39.75f, 197f, 21.5f, 18f, 45.5f, 42f, MapObject.WALL));
			objectList.add(new MapObject(39.75f, 437f, 21.5f, 18f, 45.5f, 42f, MapObject.WALL));
		    //South (Lab Interior) Walls
		    objectList.add(new MapObject(-208, 317.5f, 5f, 702f, 29, 726, MapObject.WALL));
		    // Pillars
		 	objectList.add(new MapObject(-196.875f, 197f, 22.25f, 18.5f, 46.25f, 42.5f, MapObject.WALL));
		 	objectList.add(new MapObject(-196.875f, 437.5f, 22.25f, 18.5f, 46.25f, 42.5f, MapObject.WALL));
		 	objectList.add(new MapObject(-196.875f, 673.5f, 22.25f, 10f, 46.25f, 34f, MapObject.WALL));
		 	
		 	// Breaker Box
		 	objectList.add(new MapObject(-163.625f, 672f, 14f, 4f, 64f, 54f, MapObject.BREAKERS));
		 	
		 	// *** HALLWAY ***
		 	objectList.add(new MapObject(48f, 680.25f, 5f, 24f, 29f, 48f, MapObject.WALL));
		 	//Stairs
		 	objectList.add(new MapObject(48f, 729.375f, 5f, 74.25f, 29f, 98.25f, MapObject.STAIRS, "Exit. Stairs Up"));
		 	objectList.add(new MapObject(48f, 780.75f, 5f, 28.5f, 29f, 52.5f, MapObject.WALL));
		 	objectList.add(new MapObject(50f, 797f, 4f, 4f, 24f, 54f, MapObject.EMERGENCY, "Fire Alarm")); // Fire Alarm
		 	objectList.add(new MapObject(48f, 814.375f, 5f, 30.75f, 29f, 54.75f, MapObject.WALL));
		 	objectList.add(new MapObject(48f, 894.375f, 5f, 65.25f, 29f, 89.25f, MapObject.WALL));
		 	objectList.add(new MapObject(50f, 845.75f, 4f, 32f, 24f, 82f, MapObject.EMERGENCY, "Fire Equipment")); // Fire Equipment
		 	objectList.add(new MapObject(48f, 959.625f, 5f, 65.25f, 29f, 89.25f, MapObject.DOOR, "Baldy 21. C S E Lab")); // Baldy 21, CSE Lab
		 	objectList.add(new MapObject(48f, 1076.125f, 5f, 168.25f, 29f, 187.25f, MapObject.WALL));
		 	objectList.add(new MapObject(110.25f, 1158f, 96.5f, 5f, 120.5f, 29f, MapObject.EMERGENCY, "Fire Door")); // fire Door		 	
		 	objectList.add(new MapObject(56.25f, 1158f, 11.5f, 5f, 11.5f, 29f, MapObject.WALL)); 
		 	objectList.add(new MapObject(157.875f, 1158f, 10.25f, 5f, 10.25f, 29f, MapObject.WALL));
		 	objectList.add(new MapObject(165.5f, 1122.875f, 5f, 75.25f, 29f, 94.25f, MapObject.WALL));
		 	objectList.add(new MapObject(165.5f, 1067.25f, 5f, 36f, 29f, 60f, MapObject.DOOR, "Baldy 22"));
		 	objectList.add(new MapObject(165.5f, 1044f, 5f, 10.5f, 29f, 34.5f, MapObject.WALL));
		 	objectList.add(new MapObject(165.5f, 1020.75f, 5f, 36f, 29f, 60f,  MapObject.DOOR, "Baldy 20"));
		 	objectList.add(new MapObject(165.5f, 769.625f, 5f, 466.25f, 29f, 490.25f, MapObject.WALL, "Lockers"));	 	
		 	objectList.add(new MapObject(171.375f, 536.5f, 17.75f, 5f, 41.75f, 29f, MapObject.WALL));
		 	objectList.add(new MapObject(230.25f, 533f, 5f, 7f, 29f, 31f, MapObject.WALL));
		 	objectList.add(new MapObject(228.25f, 527f, 5f, 5f, 25f, 55f, MapObject.EMERGENCY, "Public Safety Phone"));
		 	objectList.add(new MapObject(230.25f, 485f, 5f, 84f, 29f, 108f, MapObject.WALL));
		 	objectList.add(new MapObject(230.25f, 425f, 5f, 36f, 29f, 60f, MapObject.DOOR, "Baldy 16"));
		 	objectList.add(new MapObject(230.25f, 397.75f, 5f, 18.5f, 29f, 42.5f, MapObject.WALL));
		 	objectList.add(new MapObject(321.25f, 391f, 187f, 5f, 211f, 29f, MapObject.WALL));
		 	objectList.add(new MapObject(417.25f, 346.75f, 5f, 93.5f, 29f, 29f,  MapObject.DOOR, "Baldy 16 a. Fire alarm panel"));
		 	objectList.add(new MapObject(315f, 302.5f, 199.5f, 5f, 228.5f, 29f, MapObject.WALL));
		 	
		 	// Ameya and Mustafa Measurements begin here
		 	// South hallway Wall
			
		 	objectList.add(new MapObject(24f, -36.25f, 53, 5, 77, 5, MapObject.WALL));
		 	objectList.add(new MapObject(48f, -47.5f, 5, 22.5f, 29, 46.5f, MapObject.WALL));
		 	objectList.add(new MapObject(48f, -78f, 5, 38.5f, 29, 62.5f,  MapObject.DOOR, "Baldy 17. Center for literacy and reading instruction"));
		 	objectList.add(new MapObject(48f, -201.25f, 5, 208, 29, 232, MapObject.WALL)); 	
		 	objectList.add(new MapObject(48f, -324.5f, 5, 38.5f, 29, 62.5f,  MapObject.DOOR, "Baldy 15. Early Childhood Center")); 	
		 	objectList.add(new MapObject(48f, -378.75f, 5, 70, 29, 94, MapObject.WALL)); 	
		 	objectList.add(new MapObject(48f, -445f, 5, 62.5f, 29, 86.5f,  MapObject.DOOR, "Baldy 13")); 	
		 	objectList.add(new MapObject(48f, -506.25f, 5, 60, 29, 84, MapObject.WALL)); 	
		 	objectList.add(new MapObject(24f, -533.75f, 53, 5, 77, 5, MapObject.WALL));
		 	objectList.add(new MapObject(0f, -572.75f, 5, 73, 29, 97, MapObject.STAIRS, "Exit"));
		 	objectList.add(new MapObject(24f, -611.75f, 53, 5, 77, 5, MapObject.WALL));	
		 	objectList.add(new MapObject(48f, -641.25f, 5, 64, 29, 89, MapObject.WALL)); 	
		 	objectList.add(new MapObject(50f, -689f, 5, 31.5f, 25, 81.5f, MapObject.EMERGENCY, "Fire Equipment")); 	
		 	objectList.add(new MapObject(48f, -738.75f, 5, 68, 29, 92, MapObject.WALL)); 	
		 	objectList.add(new MapObject(24f, -770.25f, 53, 5, 77, 5, MapObject.WALL));
		 	objectList.add(new MapObject(0f, -807.75f, 5, 73, 29, 97,  MapObject.DOOR, "Stroller Parking. Kids' Center"));
		 	objectList.add(new MapObject(24f, -845.25f, 53, 5, 77, 5, MapObject.WALL));	
		 	objectList.add(new MapObject(48f, -1112.25f, 5, 539, 29, 563, MapObject.WALL)); 	
		 	objectList.add(new MapObject(24f, -1379.25f, 53, 5, 77, 5, MapObject.WALL));
		 	objectList.add(new MapObject(0f, -1417.75f, 5, 72, 29, 96,  MapObject.DOOR, "Child Care Center"));
		 	objectList.add(new MapObject(24f, -1451.25f, 53, 5, 77, 5, MapObject.WALL));
		 	objectList.add(new MapObject(48f, -1474.75f, 5, 42, 29, 66, MapObject.WALL)); 	
		 	objectList.add(new MapObject(50f, -1497.75f, 5, 4, 25, 54, MapObject.EMERGENCY, "Fire Alarm")); 	
		 	objectList.add(new MapObject(48f, -1505f, 5, 10.5f, 29, 34.5f, MapObject.WALL)); 	
		 	objectList.add(new MapObject(-26f, -1507.75f, 148, 5, 172, 29f, MapObject.WALL)); 	
		 	objectList.add(new MapObject(-102.5f, -1566.25f, 5, 122, 29, 146f, MapObject.ELEVATOR)); 	
		 	objectList.add(new MapObject(-102.5f, -1635.25f, 5, 21, 29, 45f, MapObject.WALL)); 	
		 	objectList.add(new MapObject(-102.5f, -1662f, 5, 53.5f, 29, 77.5f, MapObject.WALL)); 	
		 	objectList.add(new MapObject(-30.5f, -1691.25f, 149, 5f, 173, 29f, MapObject.WALL)); 	
		 	objectList.add(new MapObject(-99.5f, -1644.5f, 5, 18.5f, 25, 68.5f, MapObject.EMERGENCY, "Automated External Defibrillator")); 	
		 	
		 	// North hallway wall (from the west)
		 	
		 	objectList.add(new MapObject(217.75f, 301f, 5f, 8f, 29f, 32f, MapObject.WALL));
		 	objectList.add(new MapObject(217.75f, 275.75f, 5f, 42.5f, 29f, 66.5f,  MapObject.DOOR, "Baldy 14 A"));
		 	objectList.add(new MapObject(217.75f, 250.75f, 5f, 7.5f, 29f, 31.5f, MapObject.WALL));
		 	objectList.add(new MapObject(212.125f, 249.5f, 16.25f, 5f, 40.25f, 29f, MapObject.WALL));
		 	objectList.add(new MapObject(206.625f, 237.75f, 5f, 28.5f, 29f, 52.5f, MapObject.WALL));
		 	objectList.add(new MapObject(206.625f, 204.25f, 5f, 38.5f, 29f, 62.5f, MapObject.DOOR, "Baldy 14"));
		 	objectList.add(new MapObject(206.625f, 179.75f, 5f, 10.5f, 29f, 34.5f, MapObject.WALL));
		 	objectList.add(new MapObject(187.875f, 177f, 42.5f, 5f, 66.5f, 29f, MapObject.WALL));
		 	objectList.add(new MapObject(169.125f, 125.5f, 5f, 108f, 29f, 132f, MapObject.WALL));
		 	objectList.add(new MapObject(167.125f, 69f, 5f, 5f, 25f, 55f, MapObject.EMERGENCY, "Fire Extinguisher"));
		 	objectList.add(new MapObject(169.125f, -52f, 5f, 237f, 29f, 261f, MapObject.WALL));
			objectList.add(new MapObject(190.25f, -168f, 47.25f, 5f, 72.25f, 29f, MapObject.WALL));
			objectList.add(new MapObject(211.375f, -171.25f, 5f, 1.5f, 29f, 25.5f, MapObject.WALL));
			objectList.add(new MapObject(211.375f, -191.25f, 5f, 38.5f, 29f, 62.5f,  MapObject.DOOR, "Baldy 14. I T Lab"));
			objectList.add(new MapObject(211.375f, -219f, 5f, 17f, 29f, 41f, MapObject.WALL));
			objectList.add(new MapObject(211.375f, -246.75f, 5f, 38.5f, 29f, 62.5f,  MapObject.DOOR, "Baldy 14 A. I T Lab"));
			objectList.add(new MapObject(211.375f, -271.25f, 5f, 10.5f, 29f, 34.5f, MapObject.WALL));
			objectList.add(new MapObject(190.25f, -274f, 47.25f, 5f, 72.25f, 29f, MapObject.WALL));
			objectList.add(new MapObject(169.125f, -345f, 5f, 147f, 29f, 171f, MapObject.WALL));
			objectList.add(new MapObject(169.125f, -437.75f, 5f, 38.5f, 29f, 62.5f,  MapObject.DOOR, "Baldy 12"));
			objectList.add(new MapObject(169.125f, -532f, 5f, 150f, 29f, 174f, MapObject.WALL));
			objectList.add(new MapObject(194.125f, -604.5f, 55f, 5f, 79f, 29f, MapObject.RESTROOM, "Men's Room"));
			objectList.add(new MapObject(219.125f, -628.5f, 5f, 48f, 29f, 72f, MapObject.WALL));
			objectList.add(new MapObject(217.125f, -662f, 5f, 24f, 25f, 54f, MapObject.WATERFOUNTAIN));
			objectList.add(new MapObject(219.125f, -680f, 5f, 12f, 29f, 36f, MapObject.WALL));
			objectList.add(new MapObject(217.125f, -688.5f, 5f, 5f, 25f, 55f, MapObject.EMERGENCY, "Emergency Phone"));
			objectList.add(new MapObject(219.125f, -704f, 5f, 31f, 29f, 55f, MapObject.WALL));
			objectList.add(new MapObject(194.125f, -719.5f, 55f, 5f, 79f, 29f, MapObject.RESTROOM, "Women's Room"));
			objectList.add(new MapObject(169.125f, -934.5f, 5f, 435f, 29f, 324f, MapObject.WALL));
			objectList.add(new MapObject(194.125f, -1149.5f, 55f, 5f, 79f, 29f, MapObject.WALL));
			objectList.add(new MapObject(219.125f, -1202f, 5f, 110f, 29f, 134f, MapObject.DOOR, "Baldy 8"));
			objectList.add(new MapObject(194.125f, -1254.5f, 55f, 5f, 79f, 29f, MapObject.WALL));
			objectList.add(new MapObject(169.125f, -1307f, 5f, 110f, 29f, 134f, MapObject.WALL));
			objectList.add(new MapObject(169.125f, -1380f, 5f, 36f, 29f, 60f, MapObject.DOOR, "Baldy 6"));
			objectList.add(new MapObject(169.125f, -1426.5f, 5f, 57f, 29f, 81f, MapObject.WALL));
			objectList.add(new MapObject(169.125f, -1468f, 5f, 36f, 29f, 60f, MapObject.DOOR, "Baldy 4"));
			objectList.add(new MapObject(169.125f, -1533.75f, 5f, 95.5f, 29f, 129.5f, MapObject.WALL));
			objectList.add(new MapObject(167.125f, -1583.5f, 5f, 4f, 25f, 54f, MapObject.EMERGENCY, "Fire Extinguisher"));
			objectList.add(new MapObject(169.125f, -1591.5f, 5f, 12f, 29f, 36f, MapObject.WALL));
			objectList.add(new MapObject(169.125f, -1634.75f, 5f, 74.5f, 29f, 98.5f, MapObject.DOOR, "Baldy 2"));
			objectList.add(new MapObject(169.125f, -1703f, 5f, 62f, 29f, 86f, MapObject.WALL));
			objectList.add(new MapObject(165.625f, -1736.5f, 12f, 5f, 36f, 27f, MapObject.WALL));
			objectList.add(new MapObject(41.5f, -1714.125f, 5f, 49.75f, 29f, 73.75f, MapObject.WALL));
			
		}
		
}
