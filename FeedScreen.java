import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeedScreen extends JFrame {
    private String uname;
    Management management;
    private JTable vehicleTable;
    private JTextField filterTextField;
    private JButton filterButton;
    private List<Vehicle> allVehicles; // This should be initialized with your vehicle data
    private JFrame frame;

    public FeedScreen(String uname, JFrame frame, Management management) {
        this.uname = uname;
        this.frame = frame;
        this.management = management;

        // Initialize allVehicles with the provided list
        //this.allVehicles = vehicles;


        // Set up the frame
        setTitle("Vehicle Rental Service");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Navigation panel
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel feedPanel = new JPanel();
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS)); 
        filterTextField = new JTextField(20);
        filterButton = new JButton("Filter");
        //filterButton.addActionListener(e -> filterVehicles());
        navigationPanel.add(filterTextField);
        navigationPanel.add(filterButton);


        for (Vehicle v : management.getVehicles()) {
                JPanel vehiclePanel = createVehiclePanel(v);
        }
        // Vehicle display panel
        //vehicleTable = new JTable();
        //vehicleTable.setModel(new DefaultTableModel(new Object[]{"Make & Model", "Seats", "Rental Price", "Available"}, 0));
        //JScrollPane scrollPane = new JScrollPane(vehicleTable);

        //add(navigationPanel, BorderLayout.NORTH);
        //add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    /* 
    private void filterVehicles() {
        String searchText = filterTextField.getText().toLowerCase();
        List<Vehicle> filteredVehicles = allVehicles.stream()
                .filter(v -> v.getMakeModel().toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        updateVehicleTable(filteredVehicles);
    }*/

    private void updateVehicleTable(List<Vehicle> vehicles) {
        DefaultTableModel model = (DefaultTableModel) vehicleTable.getModel();
        model.setRowCount(0); // Clear existing data
        for (Vehicle v : vehicles) {
            String availability = v.isAvailable() ? "Yes" : "No";
            model.addRow(new Object[]{v.getMakeModel(), v.getSeats(), v.getRentalPrice(), availability});
        }
    }

    /*

    public void showAllVehicles(ArrayList<Vehicle> vehicles){
        //uses  vehicle to show vehicle beside image
        for(Vehicle v: vehicles){
            createVehiclePanel(v);
        }
       
    }*/

    public JPanel createVehiclePanel(Vehicle vehicle){
        //in this method A vehicle will be placed on a panel with model/name, image then 
        //quality, seats, rentalPrice, available and a button to rent
        JPanel vehiclePanel = new JPanel();
        
        JLabel nameLabel = new JLabel("Model/Name: " + vehicle.getMakeModel());
        ImageIcon vehicleImage = loadImageForVehicle(vehicle);
        JLabel imageLabel = new JLabel(vehicleImage);
        JLabel seatsLabel = new JLabel("Seats: " + vehicle.getSeats());
        JLabel qualityLabel = new JLabel("Quality: " + vehicle.getQuality());
        JLabel priceLabel = new JLabel("Rental Price: $" + vehicle.getRentalPrice());
        JButton rentButton = new JButton("Rent");

        vehiclePanel.add(nameLabel);
        vehiclePanel.add(imageLabel);
        vehiclePanel.add(qualityLabel);
        vehiclePanel.add(seatsLabel);
        vehiclePanel.add(priceLabel);
        vehiclePanel.add(rentButton);

        return vehiclePanel;
    }

    private static ImageIcon loadImageForVehicle(Vehicle vehicle) {
        // Load the image based on vehicle information
        // Return an ImageIcon
        // Example: return new ImageIcon("path/to/vehicle_image.png");
        return null;
    }

   /* // Main method for demonstration purposes
    public static void main(String[] args) {
        // Here you should pass the actual list of vehicles to the FeedScreen
        new FeedScreen( yourListOfVehicles );
    }*/
}
