package fixtures;

public final  class DataFixture {
    private DataFixture() {}
    public static final String TRIPS_CSV = "route_id,trip_id\n" +
          "101,NORMAL_03_101_Return_22:10\n" +
          "103,NORMAL_03_103_Go_07:20\n" +
          "104,NORMAL_03_104_Return_21:15\n" +
          "106,NORMAL_03_106_Return_12:40\n" +
          "104,NORMAL_03_104_Go_12:40\n" +
          "101,NORMAL_03_101_Go_17:20\n" +
          "105,NORMAL_03_105_Go_08:15\n" +
          "107,NORMAL_03_107_Return_15:00\n" +
          "104,NORMAL_03_104_Return_14:20\n" +
          "105,NORMAL_03_105_Return_13:10\n" +
          "103,NORMAL_03_103_Return_17:00\n" +
          "105,NORMAL_03_105_Return_10:40\n" +
          "102,NORMAL_03_102_Go_17:50\n" +
          "106,NORMAL_03_106_Go_19:45\n" +
          "101,NORMAL_03_101_Return_17:45\n";


  public static final String STOPS_CSV = "stop_id,stop_name\n" +
          "2,AL Masjid Al-nabawi (Clock Roundabout)\n" +
          "3,Uhud battlefield\n" +
          "4,Al Quiblatain Mosque\n" +
          "5,The Trench Battlefield\n" +
          "7,AL Masjid Al-nabawi (AL Baqe)\n" +
          "9,AL Masjid Al-nabawi (Manakha Square)\n" +
          "10,Quba Mosque\n" +
          "11,AL Masjid Al-nabawi (AL Salam Gate)\n" +
          "13,Railway Station\n";



  public static final String STOP_TIMES_CSV = "trip_id,arrival_time,stop_id\n" +
          "NORMAL_03_101_Return_22:10,22:10:00,2\n" +
          "NORMAL_03_103_Go_07:20,07:20:00,2\n" +
          "NORMAL_03_104_Return_21:15,21:15:00,2\n" +
          "NORMAL_03_106_Return_12:40,12:40:00,2\n" +
          "NORMAL_03_104_Go_12:40,12:40:00,2\n" +
          "NORMAL_03_101_Go_17:20,17:20:00,2\n" +
          "NORMAL_03_105_Go_08:15,08:15:00,2\n" +
          "NORMAL_03_107_Return_15:00,15:00:00,1\n" +
          "NORMAL_03_104_Return_14:20,14:20:00,2\n" +
          "NORMAL_03_105_Return_13:10,13:10:00,2\n" +
          "NORMAL_03_103_Return_17:00,17:00:00,1\n" +
          "NORMAL_03_105_Return_10:40,10:40:00,2\n" +
          "NORMAL_03_102_Go_17:50,17:50:00,2\n" +
          "NORMAL_03_106_Go_19:45,19:45:00,2\n" +
          "NORMAL_03_101_Return_17:45,17:45:00,1\n";

  }
