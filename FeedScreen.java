import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JComboBox<String> filterComboBox;
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
        
        
        filterTextField = new JTextField(20);
        filterButton = new JButton("Filter");
        

        String[] filterOptions = {"Price High to Low", "Price Low to High", "Seats Large to Small"};
        filterComboBox = new JComboBox<>(filterOptions);
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
            //JScrollPane scrollPane = new JScrollPane(feedPanel);
            GridLayout gridLayout = new GridLayout(3, 2);
            feedPanel.setLayout(gridLayout);
            feedPanel.setMaximumSize(new Dimension(2000, Integer.MAX_VALUE));
            //feedPanel.add(scrollPane);
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

    public JPanel createVehiclePanel(Vehicle vehicle) {
        JPanel vehiclePanel = new JPanel();
        vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.Y_AXIS)); // Vertical layout
    
        JLabel nameLabel = new JLabel("Model/Name: " + vehicle.getMakeModel());
        ImageIcon vehicleImage = loadImageForVehicle(vehicle);
        JLabel imageLabel = new JLabel(vehicleImage);
        imageLabel.setPreferredSize(new Dimension(200, 150));
    
        JLabel seatsLabel = new JLabel("Seats: " + vehicle.getSeats());
        JLabel qualityLabel = new JLabel("Quality: " + vehicle.getQuality());
        JLabel priceLabel = new JLabel("Rental Price: $" + vehicle.getRentalPrice());
        JButton rentButton = new JButton("Rent");
        rentButton.addActionListener(rentButtonActionListener());
    
        // Set maximum width for the vehicle panel
        vehiclePanel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE)); // Adjust as needed
    
        // Add components to the panel
        vehiclePanel.add(nameLabel);
        vehiclePanel.add(imageLabel);
        vehiclePanel.add(qualityLabel);
        vehiclePanel.add(seatsLabel);
        vehiclePanel.add(priceLabel);
        vehiclePanel.add(rentButton);
        vehiclePanel.add(filterButton);
    
        return vehiclePanel;
    }

    private void applyFilter(String selectedFilter) {
        switch (selectedFilter) {
            case "Price High to Low":
                management.sortBy("PriceHighLow", "");
                break;
            case "Price Low to High":
                management.sortBy("PriceLowHigh", "");
                break;
            case "Seats Large to Small":
                management.sortBy("SizeLargestSmall", "");
                break;
            case "Lowend Quality":
                management.sortBy("Quality", "lowend");
                break;
            case "MidRange Quality":
                management.sortBy("Quality", "midrange");
                break;
            case "HighEnd Quality":
                management.sortBy("Quality", "highend");
                break;
            default:
                // Handle unknown filter option
                break;
        }
    }
    
    
    
    public static ActionListener rentButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame rentformFrame = new JFrame();

                rentformFrame.setTitle("Vehicle Rental Service");
                rentformFrame.setSize(800, 600);
                rentformFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                rentformFrame.setLayout(new BorderLayout());

                
                JPanel rentform = new JPanel();
                JLabel start_endDate = new JLabel("Start and end date: ");
                JTextField dateField1 = new JTextField();
                JTextField dateField2 = new JTextField();
                JLabel budget = new JLabel("Budget: ");

                rentformFrame.add(rentform);
                rentform.add(start_endDate);
                rentform.add(dateField1);
                rentform.add(dateField2);
                rentform.add(budget);

                rentformFrame.setVisible(true);
                

            }
        };
    }
    
    

    private static ImageIcon loadImageForVehicle(Vehicle vehicle) {
        String imagePath = "images/" + vehicle.getMakeModel() + ".jpg";
        System.out.println("Image path: " + imagePath);
        ImageIcon icon = new ImageIcon(imagePath);
        
        return icon;
    }

}
