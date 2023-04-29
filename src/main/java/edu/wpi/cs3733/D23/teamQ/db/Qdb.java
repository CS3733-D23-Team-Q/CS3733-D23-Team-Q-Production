package edu.wpi.cs3733.D23.teamQ.db;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.dao.Subscriber;
import edu.wpi.cs3733.D23.teamQ.db.impl.*;
import edu.wpi.cs3733.D23.teamQ.db.obj.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Qdb {
  private ArrayList<Subscriber> subscribers = new ArrayList<>();

  private AccountDaoImpl accountTable;

  private LocationDaoImpl locationTable;

  private NodeDaoImpl nodeTable;
  private EdgeDaoImpl edgeTable;
  private MoveDaoImpl moveTable;
  private ProfileImageDaoImpl profileImageTable;

  private QuestionDaoImpl questionTable;

  private ConferenceRequestDaoImpl conferenceRequestTable;
  private FlowerRequestDaoImpl flowerRequestTable;
  private MealRequestDaoImpl mealRequestTable;
  private FurnitureRequestDaoImpl furnitureRequestTable;
  private PatientTransportRequestDaoImpl patientTransportRequestTable;
  private OfficeSuppliesRequestDaoImpl officeSuppliesRequestTable;
  private MedicalSuppliesRequestDaoImpl medicalSuppliesRequestTable;
  private ServiceRequestDaoImpl serviceRequestTable;

  private SignDaoImpl signTable;

  private MessageDaoImpl messageTable;
  private AlertDaoImpl alertTable;
  private SettingsDaoImpl settingsTable;
  private DefaultLocationDaoImpl defaultLocationsTable;

  private Account messagingAccount = null;

  private int kiosk;

  private String date;

  private static Qdb single_instance = null;

  public static synchronized Qdb getInstance() {
    if (single_instance == null) single_instance = new Qdb();
    return single_instance;
  }

  private Qdb() {
    accountTable = AccountDaoImpl.getInstance();
    locationTable = LocationDaoImpl.getInstance();
    nodeTable = NodeDaoImpl.getInstance(locationTable);
    edgeTable = EdgeDaoImpl.getInstance(nodeTable);
    moveTable = MoveDaoImpl.getInstance(nodeTable);
    questionTable = QuestionDaoImpl.getInstance();
    conferenceRequestTable = ConferenceRequestDaoImpl.getInstance(accountTable, nodeTable);
    flowerRequestTable = FlowerRequestDaoImpl.getInstance(accountTable, nodeTable);
    mealRequestTable = MealRequestDaoImpl.getInstance(accountTable, nodeTable);
    furnitureRequestTable = FurnitureRequestDaoImpl.getInstance(accountTable, nodeTable);
    patientTransportRequestTable =
        PatientTransportRequestDaoImpl.getInstance(accountTable, nodeTable);
    officeSuppliesRequestTable = OfficeSuppliesRequestDaoImpl.getInstance(accountTable, nodeTable);
    medicalSuppliesRequestTable =
        MedicalSuppliesRequestDaoImpl.getInstance(accountTable, nodeTable);
    serviceRequestTable = ServiceRequestDaoImpl.getInstance(accountTable, nodeTable);
    profileImageTable = ProfileImageDaoImpl.getInstance();

    signTable = SignDaoImpl.getInstance();

    messageTable = MessageDaoImpl.getInstance(accountTable);
    alertTable = AlertDaoImpl.getInstance();
    settingsTable = SettingsDaoImpl.getInstance();
    defaultLocationsTable = DefaultLocationDaoImpl.getInstance();
  }

  private boolean updateTimestamp(String tableName) {
    try (Connection connection = GenDao.connect();
        PreparedStatement st =
            connection.prepareStatement(
                "UPDATE \"timestamp\" SET updated_timestamp = ? WHERE \"tableName\" = ?")) {
      st.setLong(1, System.currentTimeMillis());
      st.setString(2, tableName);
      st.executeUpdate();
      connection.close();
      st.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public void subscribe(Subscriber s) {
    if (!subscribers.contains(s)) {
      System.out.println("Adding subscriber");
      subscribers.add(s);
    }
  }

  public void unsubscribe(Subscriber s) {
    if (!subscribers.contains(s)) {
      System.out.println("Removing subscriber");
      subscribers.remove(s);
    }
  }

  public synchronized void notifySubscribers(List<String> context) {
    for (Subscriber s : subscribers) {
      s.update(context);
    }
  }

  public Account retrieveAccount(String username) {
    return accountTable.retrieveRow(username);
  }

  public ArrayList<Account> retrieveAccounts(String email) {
    return (ArrayList<Account>) accountTable.retrieveRows(email);
  }

  public boolean updateAccount(String username, Account a) {
    updateTimestamp("account");
    return accountTable.updateRow(username, a);
  }

  public boolean deleteAccount(String username) {
    updateTimestamp("account");
    return accountTable.deleteRow(username);
  }

  public boolean addAccount(Account a) {
    updateTimestamp("account");
    return accountTable.addRow(a);
  }

  public int getAccountIndex(String username) {
    return accountTable.getIndex(username);
  }

  public ObservableList<String> getAllNames() {
    return accountTable.getAllNames();
  }

  public List<Integer> getAccountIndexes(String email) {
    return accountTable.getIndexes(email);
  }

  public Account getAccountFromUsername(String UN) {
    return accountTable.getAccountFromUN(UN);
  }

  public ArrayList<Account> retrieveAllAccounts() {
    return (ArrayList<Account>) accountTable.getAllRows();
  }

  public List<Sign> retrieveSigns(int kiosk, String date) {
    return signTable.retrieveRows(kiosk, date);
  }

  public boolean addSign(Sign a) {
    return signTable.addRow(a);
  }

  public ArrayList<Sign> retrieveAllSigns() {
    return (ArrayList<Sign>) signTable.getAllRows();
  }

  public ConferenceRequest retrieveConferenceRequest(int requestID) {
    return conferenceRequestTable.retrieveRow(requestID);
  }

  public boolean updateConferenceRequest(int requestID, ConferenceRequest cr) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.updateRow(requestID, cr);
    return conferenceRequestTable.updateRow(requestID, cr);
  }

  public boolean deleteConferenceRequest(int requestID) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.deleteRow(requestID);
    return conferenceRequestTable.deleteRow(requestID);
  }

  public boolean addConferenceRequest(ConferenceRequest cr) {
    updateTimestamp("serviceRequest");
    conferenceRequestTable.addRow(cr);
    return true;
  }

  public ArrayList<ConferenceRequest> retrieveAllConferenceRequests() {
    return (ArrayList<ConferenceRequest>) conferenceRequestTable.getAllRows();
  }

  public FlowerRequest retrieveFlowerRequest(int requestID) {
    return flowerRequestTable.retrieveRow(requestID);
  }

  public boolean updateFlowerRequest(int requestID, FlowerRequest fr) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.updateRow(requestID, fr);
    return flowerRequestTable.updateRow(requestID, fr);
  }

  public boolean deleteFlowerRequest(int requestID) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.deleteRow(requestID);
    return flowerRequestTable.deleteRow(requestID);
  }

  public boolean addFlowerRequest(FlowerRequest fr) {
    updateTimestamp("serviceRequest");
    flowerRequestTable.addRow(fr);
    return true;
  }

  public ArrayList<FlowerRequest> retrieveAllFlowerRequests() {
    return (ArrayList<FlowerRequest>) flowerRequestTable.getAllRows();
  }

  public List<ServiceRequest> retrieveAllServiceRequests() {
    return serviceRequestTable.getAllRows();
  }

  public List<ServiceRequest> retrieveUserServiceRequests(String user) {
    return serviceRequestTable.getUserRows(user);
  }

  public ObservableList<ServiceRequest> retrieveUserAssignServiceRequests(String user) {
    return serviceRequestTable.getUserAssignedRows(user);
  }

  public ObservableList<ServiceRequest> getUserRequestedRows(String user) {
    return serviceRequestTable.getUserRequestedRows(user);
  }

  public ServiceRequest retrieveServiceRequest(int requestID) {
    return serviceRequestTable.retrieveRow(requestID);
  }

  public Edge retrieveEdge(int edgeID) {
    return edgeTable.retrieveRow(edgeID);
  }

  public boolean updateEdge(int edgeID, Edge e) {
    updateTimestamp("edge");
    return edgeTable.updateRow(edgeID, e);
  }

  public boolean deleteEdge(int edgeID) {
    updateTimestamp("edge");
    return edgeTable.deleteRow(edgeID);
  }

  public boolean addEdge(Edge e) {
    updateTimestamp("edge");
    return edgeTable.addRow(e);
  }

  public List<Edge> getEdges(int nodeID) {
    return edgeTable.getEdges(nodeID);
  }

  public ArrayList<Edge> retrieveAllEdges() {
    return (ArrayList<Edge>) edgeTable.getAllRows();
  }

  public boolean edgesToCSV(String filename) {
    return edgeTable.toCSV(filename);
  }

  public boolean edgesFromCSV(String filename) {
    return edgeTable.importCSV(filename);
  }

  public Node retrieveNode(int nodeID) {
    return nodeTable.retrieveRow(nodeID);
  }

  public boolean updateNode(int nodeID, Node n) {
    updateTimestamp("node");
    return nodeTable.updateRow(nodeID, n);
  }

  public boolean deleteNode(int nodeID) {
    updateTimestamp("node");
    return nodeTable.deleteRow(nodeID);
  }

  public boolean addNode(Node n) {
    updateTimestamp("node");
    return nodeTable.addRow(n);
  }

  public ArrayList<Node> retrieveAllNodes() {
    return (ArrayList<Node>) nodeTable.getAllRows();
  }

  public boolean nodesToCSV(String filename) {
    return nodeTable.toCSV(filename);
  }

  public boolean nodesFromCSV(String filename) {
    return nodeTable.importCSV(filename);
  }

  public Location retrieveLocation(int nodeID) {
    return locationTable.retrieveRow(nodeID);
  }

  public boolean updateLocation(int nodeID, Location l) {
    updateTimestamp("locationName");
    return locationTable.updateRow(nodeID, l);
  }

  public boolean deleteLocation(int nodeID) {
    updateTimestamp("locationName");
    return locationTable.deleteRow(nodeID);
  }

  public boolean addLocation(Location l) {
    updateTimestamp("locationName");
    return locationTable.addRow(l);
  }

  public ArrayList<Location> retrieveAllLocations() {
    return (ArrayList<Location>) locationTable.getAllRows();
  }

  public boolean locationsToCSV(String filename) {
    return locationTable.toCSV(filename);
  }

  public boolean locationsFromCSV(String filename) {
    return locationTable.importCSV(filename);
  }

  public ObservableList<String> getAllLongNames(String[] nodeTypes) {
    return locationTable.getAllLongNames(nodeTypes);
  }

  public ObservableList<String> getAllLongNames() {
    return locationTable.getAllLongNames();
  }

  public Move retrieveMove(int moveID) {
    return moveTable.retrieveRow(moveID);
  }

  public boolean updateMove(int moveID, Move m) {
    updateTimestamp("move");
    return moveTable.updateRow(moveID, m);
  }

  public boolean deleteMove(int moveID) {
    updateTimestamp("move");
    return moveTable.deleteRow(moveID);
  }

  public boolean addMove(Move m) {
    updateTimestamp("move");
    return moveTable.addRow(m);
  }

  public ArrayList<Move> retrieveAllMoves() {
    return (ArrayList<Move>) moveTable.getAllRows();
  }

  public boolean movesToCSV(String filename) {
    return moveTable.toCSV(filename);
  }

  public boolean movesFromCSV(String filename) {
    return moveTable.importCSV(filename);
  }

  public Question retrieveQuestion(int ID) {
    return questionTable.retrieveRow(ID);
  }

  public Question retrieveQuestion(String question) {
    return questionTable.retrieveRow(question);
  }

  public boolean updateQuestion(int ID, Question q) {
    updateTimestamp("question");
    return questionTable.updateRow(ID, q);
  }

  public boolean deleteQuestion(int ID) {
    updateTimestamp("question");
    return questionTable.deleteRow(ID);
  }

  public ArrayList<Question> retrieveAllQuestions() {
    return (ArrayList<Question>) questionTable.getAllRows();
  }

  public boolean addQuestion(Question q) {
    updateTimestamp("question");
    return questionTable.addRow(q);
  }

  public int getQuestionIndex(int ID) {
    return questionTable.getIndex(ID);
  }

  public int getQuestionIndex(String question) {
    return questionTable.getIndex(question);
  }

  public MealRequest retrieveMealRequest(int requestID) {
    return mealRequestTable.retrieveRow(requestID);
  }

  public boolean updateMealRequest(int requestID, MealRequest mr) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.updateRow(requestID, mr);
    return mealRequestTable.updateRow(requestID, mr);
  }

  public boolean deleteMealRequest(int requestID) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.deleteRow(requestID);
    return mealRequestTable.deleteRow(requestID);
  }

  public boolean addMealRequest(MealRequest mr) {
    updateTimestamp("serviceRequest");
    mealRequestTable.addRow(mr);
    return true;
  }

  public ArrayList<MealRequest> retrieveAllMealRequests() {
    return (ArrayList<MealRequest>) mealRequestTable.getAllRows();
  }

  public FurnitureRequest retrieveFurnitureRequest(int requestID) {
    return furnitureRequestTable.retrieveRow(requestID);
  }

  public boolean updateFurnitureRequest(int requestID, FurnitureRequest fr) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.updateRow(requestID, fr);
    return furnitureRequestTable.updateRow(requestID, fr);
  }

  public boolean deleteFurnitureRequest(int requestID) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.deleteRow(requestID);
    return furnitureRequestTable.deleteRow(requestID);
  }

  public boolean addFurnitureRequest(FurnitureRequest fr) {
    updateTimestamp("serviceRequest");
    furnitureRequestTable.addRow(fr);
    return true;
  }

  public ArrayList<FurnitureRequest> retrieveAllFurnitureRequests() {
    return (ArrayList<FurnitureRequest>) furnitureRequestTable.getAllRows();
  }

  public PatientTransportRequest retrievePatientTransportRequest(int requestID) {
    return patientTransportRequestTable.retrieveRow(requestID);
  }

  public boolean updatePatientTransportRequest(int requestID, PatientTransportRequest ptr) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.updateRow(requestID, ptr);
    return patientTransportRequestTable.updateRow(requestID, ptr);
  }

  public boolean deletePatientTransportRequest(int requestID) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.deleteRow(requestID);
    return patientTransportRequestTable.deleteRow(requestID);
  }

  public boolean addPatientTransportRequest(PatientTransportRequest ptr) {
    updateTimestamp("serviceRequest");
    patientTransportRequestTable.addRow(ptr);
    return true;
  }

  public ArrayList<PatientTransportRequest> retrieveAllPatientTransportRequests() {
    return (ArrayList<PatientTransportRequest>) patientTransportRequestTable.getAllRows();
  }

  public OfficeSuppliesRequest retrieveOfficeSuppliesRequest(int requestID) {
    return officeSuppliesRequestTable.retrieveRow(requestID);
  }

  public boolean updateOfficeSuppliesRequest(int requestID, OfficeSuppliesRequest osr) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.updateRow(requestID, osr);
    return officeSuppliesRequestTable.updateRow(requestID, osr);
  }

  public boolean deleteOfficeSuppliesRequest(int requestID) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.deleteRow(requestID);
    return officeSuppliesRequestTable.deleteRow(requestID);
  }

  public boolean addOfficeSuppliesRequest(OfficeSuppliesRequest osr) {
    updateTimestamp("serviceRequest");
    officeSuppliesRequestTable.addRow(osr);
    return true;
  }

  public ArrayList<OfficeSuppliesRequest> retrieveAllOfficeSuppliesRequests() {
    return (ArrayList<OfficeSuppliesRequest>) officeSuppliesRequestTable.getAllRows();
  }

  public Node getNodeFromLocation(String lName) {
    return nodeTable.retrieveRow(locationTable.getNodeFromLocation(lName));
  }

  public String getEmailWithAUsername(String username) {
    return accountTable.getEmailWithUsername(username);
  }

  public MedicalSuppliesRequest retrieveMedicalSuppliesRequest(int requestID) {
    return medicalSuppliesRequestTable.retrieveRow(requestID);
  }

  public boolean updateMedicalSuppliesRequest(int requestID, MedicalSuppliesRequest msr) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.updateRow(requestID, msr);
    return medicalSuppliesRequestTable.updateRow(requestID, msr);
  }

  public boolean deleteMedicalSuppliesRequest(int requestID) {
    updateTimestamp("serviceRequest");
    serviceRequestTable.deleteRow(requestID);
    return medicalSuppliesRequestTable.deleteRow(requestID);
  }

  public boolean addMedicalSuppliesRequest(MedicalSuppliesRequest msr) {
    updateTimestamp("serviceRequest");
    medicalSuppliesRequestTable.addRow(msr);
    return true;
  }

  public ArrayList<MedicalSuppliesRequest> retrieveAllMedicalSuppliesRequests() {
    return (ArrayList<MedicalSuppliesRequest>) medicalSuppliesRequestTable.getAllRows();
  }

  public ServiceRequest retrieveLastRequest() {
    return serviceRequestTable.getAllRows().get(serviceRequestTable.getAllRows().size() - 1);
  }

  public boolean deleteServiceRequest(int requestID) {
    StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    System.out.println(stackTraceElements);
    updateTimestamp("serviceRequest");
    return serviceRequestTable.deleteRow(requestID);
  }

  public boolean updateServiceRequest(int requestID, ServiceRequest sr) {
    StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    System.out.println(stackTraceElements);
    updateTimestamp("serviceRequest");
    return serviceRequestTable.updateRow(requestID, sr);
  }

  public List<ProfileImage> getAllProfileImages() {
    return profileImageTable.getAllRows();
  }

  public ProfileImage retrieveProfileImage(String username) {
    return profileImageTable.retrieveRow(username);
  }

  public boolean updateProfileImage(String username, ProfileImage x) throws SQLException {
    updateTimestamp("profileImage");
    return profileImageTable.updateRow(username, x);
  }

  public boolean deleteProfileImage(String username) throws SQLException {
    return profileImageTable.deleteRow(username);
  }

  public boolean addProfileImage(ProfileImage x) {
    updateTimestamp("profileImage");
    return profileImageTable.addRow(x);
  }

  public byte[] convertImageToBytea(Image image) {
    return profileImageTable.convertImageToBytea(image);
  }

  public Image convertByteaToImage(byte[] imageData) {
    return profileImageTable.convertByteaToImage(imageData);
  }

  public int getProfileImageIndex(String username) {
    return profileImageTable.getIndex(username);
  }

  public ObservableList<Message> retrieveMessages(String p1, String p2) {
    return messageTable.retrieveMessages(p1, p2);
  }

  public ObservableList<Message> retrieveConversations(String username) {
    return messageTable.retrieveConversations(username);
  }

  public int getNumUnread(String username) {
    return messageTable.getNumUnread(username);
  }

  public boolean addMessage(Message message) {
    updateTimestamp("message");
    return messageTable.addRow(message);
  }

  public synchronized boolean populate(ArrayList<String> tableNames) {
    for (String tableName : tableNames) {
      switch (tableName) {
        case "account":
          accountTable.populate();
        case "edge":
          edgeTable.populate();
        case "locationName":
          locationTable.populate();
        case "move":
          moveTable.populate();
        case "node":
          nodeTable.populate();
        case "profileImage":
          profileImageTable.populate();
        case "message":
          messageTable.populate();
        case "sign":
          signTable.populate();
        case "alert":
          alertTable.populate();
        case "serviceRequest":
          serviceRequestTable.populate();
          conferenceRequestTable.populate();
          flowerRequestTable.populate();
          furnitureRequestTable.populate();
          mealRequestTable.populate();
          medicalSuppliesRequestTable.populate();
          officeSuppliesRequestTable.populate();
          patientTransportRequestTable.populate();
        case "settings":
          settingsTable.populate();
        case "defaultLocation":
          defaultLocationsTable.populate();
      }
    }
    Platform.runLater(() -> notifySubscribers(tableNames));
    return true;
  }

  public void setMessagingAccount(Account a) {
    messagingAccount = a;
  }

  public Account getMessagingAccount() {
    return messagingAccount;
  }

  public void setKiosk(int k) {
    kiosk = k;
  }

  public int getKiosk() {
    return kiosk;
  }

  public void setDate(String d) {
    date = d;
  }

  public String getDate() {
    return date;
  }

  public Alert retrieveAlert(int ID) {
    return alertTable.retrieveRow(ID);
  }

  public boolean updateAlert(int ID, Alert a) {
    updateTimestamp("alert");
    alertTable.updateRow(ID, a);
    return alertTable.updateRow(ID, a);
  }

  public boolean deleteAlert(int ID) {
    updateTimestamp("alert");
    alertTable.deleteRow(ID);
    return alertTable.deleteRow(ID);
  }

  public boolean addAlert(Alert a) {
    updateTimestamp("alert");
    alertTable.addRow(a);
    return true;
  }

  public List<Alert> retrieveAllAlerts() {
    return alertTable.getAllRows();
  }

  public boolean exportToCSV(String tableName) {
    return csvMaker(tableName, "");
  }

  public boolean exportToCSV(String tableName, String filename) {
    return csvMaker(tableName, filename);
  }

  private boolean csvMaker(String tableName, String filename) {
    GenDao table = nameToTable(tableName);
    if (filename.equals("")) {
      filename = table.getFileName();
    }
    try {
      File myObj = new File(filename);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      }
      FileWriter myWriter = new FileWriter(filename);
      for (Object o : table.getAllRows()) {
        myWriter.write(o.toString() + "\n");
      }
    } catch (IOException e) {
      return false;
    }
    return true;
  }

  private GenDao nameToTable(String tableName) {
    switch (tableName) {
      case "Accounts":
        return accountTable;
      case "Edges":
        return edgeTable;
      case "Locations":
        return locationTable;
      case "Moves":
        return moveTable;
      case "Nodes":
        return nodeTable;
      case "Messages":
        return messageTable;
      case "Alerts":
        return alertTable;
      case "Signs":
        return signTable;
      case "Service requests":
        return serviceRequestTable;
    }
    return null;
  }

  public ObservableList<ServiceRequest> getAllServiceRequestsObservable() {
    return serviceRequestTable.getAllRequestsObservable();
  }

  public ObservableList<ServiceRequest> getAllOutstandingServingRequests() {
    return serviceRequestTable.getAllOutstandingRequestsObservable();
  }

  public ObservableList<ServiceRequest> getUserAssignedOutstandingRows(String user) {
    return serviceRequestTable.getUserAssignedOutstandingRows(user);
  }

  public ObservableList<ServiceRequest> getUserRequestedOutstandingRows(String user) {
    return serviceRequestTable.getUserRequestedOutstandingRows(user);
  }

  public List<Settings> getAllSettings() {
    return settingsTable.getAllRows();
  }

  public Settings retrieveSettings(String username) {
    return settingsTable.retrieveRow(username);
  }

  public boolean updateSettingsRow(String username, Settings x) {
    return settingsTable.updateRow(username, x);
  }

  public boolean deleteSettingsRow(String username) {
    return settingsTable.deleteRow(username);
  }

  public boolean addSettingsRow(Settings x) {
    return settingsTable.addRow(x);
  }

  public Location retrieveLocationFromLongName(String lName) {
    return locationTable.retrieveLocationFromLongName(lName);
  }

  public List<DefaultLocation> getAllDefaultLocations() {
    return defaultLocationsTable.getAllRows();
  }

  public DefaultLocation retrieveDefaultLocation(int id) {
    return defaultLocationsTable.retrieveRow(id);
  }

  public boolean updateDefaultLocation(int id, DefaultLocation x) {
    return defaultLocationsTable.updateRow(id, x);
  }

  public boolean deleteDefaultLocation(int id) {
    return defaultLocationsTable.deleteRow(id);
  }

  public boolean addDefaultLocation(DefaultLocation x) {
    return defaultLocationsTable.addRow(x);
  }
}
