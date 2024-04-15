import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeedScreen extends JFrame {
    private String uname;
    private Management management;
    private JTable vehicleTable;
    private JTextField filterTextField;
    private JButton filterButton;
    //private List<Vehicle> allVehicles; // This should be initialized with your vehicle data
    private JFrame frame;
    ArrayList<Vehicle> vehicles;

    public FeedScreen(String uname, JFrame frame, Management management) {
        this.uname = uname;
        this.frame = frame;
        this.management = management;
        this.vehicles = management.getVehicles();

       

        // Set up the frame
        setTitle("Vehicle Rental Service");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

    
        
        JLabel titleLabel = new JLabel("Vehicles");
        JPanel mainPanel = new JPanel();
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));
        navigationPanel.add(titleLabel);
       
        JPanel feedPanel = new JPanel();
        feedPanel.setLayout(new GridLayout(0,3)); 
        
        /* 
        for (Vehicle v : management.getVehicles()) {
            JPanel vehiclePanel = createVehiclePanel(v);
            feedPanel.add(vehiclePanel); 
        }*/


        System.out.println("lets start here");
        if(!vehicles.isEmpty()){
            System.out.println("The problem is here");
        }
        //ArrayList<Vehicle> vehicles = management.getVehicles();
        showAllVehicles(vehicles, feedPanel);

        System.out.println("baii");

        JScrollPane scrollPane = new JScrollPane(feedPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        //filterTextField = new JTextField(20);
        filterButton = new JButton("Filter");
        filterButton.addActionListener(e -> filterVehicles());
        //mainPanel.add(navigationPanel);
        //navigationPanel.add(filterTextField);
        navigationPanel.add(filterButton);
        //mainPanel.add(feedPanel);

        add(navigationPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    
    private void filterVehicles() {
        String searchText = filterTextField.getText().toLowerCase();
        List<Vehicle> filteredVehicles = vehicles.stream()
                .filter(v -> v.getMakeModel().toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        //updateVehicleTable(filteredVehicles);
    }

    /* 
    private void updateVehicleTable(List<Vehicle> vehicles) {
        DefaultTableModel model = (DefaultTableModel) vehicleTable.getModel();
        model.setRowCount(0); // Clear existing data
        for (Vehicle v : vehicles) {
            String availability = v.isAvailable() ? "Yes" : "No";
            model.addRow(new Object[]{v.getMakeModel(), v.getSeats(), v.getRentalPrice(), availability});
        }
    }*/

    

    public void showAllVehicles(ArrayList<Vehicle> vehicles, JPanel feedPanel) {
        if(!vehicles.isEmpty()){
            System.out.println("lets see");
            for (Vehicle v : vehicles) {
                //System.out.println("Vehicle: " + v.getMakeModel());
                System.out.println("woii");
                feedPanel.add(createVehiclePanel(v));
                System.out.println("no up more");
            }
            feedPanel.revalidate();
            feedPanel.repaint();
        }
        else {
            System.out.println("No vehicles to display.");
        }
    }

    public JPanel createVehiclePanel(Vehicle vehicle){
        System.out.println("in create vehicle");
        JPanel vehiclePanel = new JPanel();
        
        JLabel nameLabel = new JLabel("Model/Name: " + vehicle.getMakeModel());
        System.out.println("right here");
        ImageIcon vehicleImage = loadImageForVehicle(vehicle);
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(vehicleImage);
        imageLabel.setPreferredSize(new Dimension(200, 150));

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
        String imagePath = "images/" + vehicle.getMakeModel() + ".jpg";
        System.out.println("Image path: " + imagePath);
        ImageIcon icon = new ImageIcon(imagePath);
        
        return icon;
    }

}
