import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeedScreen extends JFrame {
    private static String uname;
    private static Management management;
    private JTable vehicleTable;
    private JTextField filterTextField;
    private JButton filterButton;
    private JButton newVehicleButton;
    private JTextField dateField1;
    private JTextField dateField2;
    JTextField budgetField;
    private Vehicle currentVehicle;
    private JPanel navigationPanel = new JPanel();
    private JPanel filterPanel = new JPanel();

    JRadioButton priceHighToLowCheckbox = new JRadioButton();
    JRadioButton priceLowToHighCheckbox = new JRadioButton();
    JRadioButton sizeLargestToSmallestCheckbox = new JRadioButton();
    JRadioButton sizeSmallestToLargestCheckbox = new JRadioButton();

    ButtonGroup buttonGroup = new ButtonGroup();
    ButtonGroup buttonGroup2 = new ButtonGroup();


    //checkboxes
    //JCheckBox priceHighToLowCheckbox = new JCheckBox();
    //JCheckBox priceLowToHighCheckbox = new JCheckBox();
    //JCheckBox sizeLargestToSmallestCheckbox = new JCheckBox();
    //JCheckBox sizeSmallestToLargestCheckbox = new JCheckBox();

    JLabel lowHigh = new JLabel("Price Low to High");
    JLabel highLow = new JLabel("Price High to Low");
    JLabel smallLarge = new JLabel("Smallest to Largest");
    JLabel largeSmall = new JLabel("Largest to Smallest");

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
       
        navigationPanel.setLayout(new GridLayout(5,2));
        //navigationPanel.add(titleLabel);
       
        JPanel feedPanel = new JPanel();
        feedPanel.setLayout(new GridLayout(0,3)); 
        
        buttonGroup.add(priceHighToLowCheckbox);
        buttonGroup.add(priceLowToHighCheckbox);

        buttonGroup2.add(sizeSmallestToLargestCheckbox);
        buttonGroup2.add(sizeLargestToSmallestCheckbox);

    
        navigationPanel.add(highLow);
        navigationPanel.add(priceHighToLowCheckbox);
        navigationPanel.add(lowHigh); 
        navigationPanel.add(priceLowToHighCheckbox); 
        navigationPanel.add(largeSmall);
        navigationPanel.add(sizeLargestToSmallestCheckbox);
        navigationPanel.add(smallLarge);
        navigationPanel.add(sizeSmallestToLargestCheckbox);
        //filterTextField = new JTextField(20);
        filterButton = new JButton("Filter");
        newVehicleButton = new JButton("New");
        

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
        //filterButton = new JButton("Filter");
        filterButton.addActionListener(e -> filterVehicles());
        newVehicleButton.addActionListener(e -> newVehicle());
        //mainPanel.add(navigationPanel);
        //navigationPanel.add(filterTextField);
        navigationPanel.add(filterButton);
        navigationPanel.add(newVehicleButton);
        //mainPanel.add(feedPanel);

        add(navigationPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    
    private void filterVehicles() {
        String searchText = filterTextField.getText().toLowerCase();
        ArrayList<Vehicle> filteredVehicles = vehicles;

                //filter(v -> v.getMakeModel().toLowerCase().contains(searchText))
                //.collect(Collectors.toList());
                navigationPanel.add(filterPanel);
                
                boolean sortByPriceHighToLow = priceHighToLowCheckbox.isSelected();
                boolean sortByPriceLowToHigh = priceLowToHighCheckbox.isSelected();
                boolean sortBySizeLargestToSmallest = sizeLargestToSmallestCheckbox.isSelected();
                boolean sortBySizeSmallestToLargest = sizeSmallestToLargestCheckbox.isSelected();

                // Group 1: Price sorting (only one option can be selected)
                if (sortByPriceHighToLow) {
                    // Implement sorting by price high to low
                    // Add filtered vehicles to 'filteredVehicles'
                } else if (sortByPriceLowToHigh) {
                    // Implement sorting by price low to high
                    // Add filtered vehicles to 'filteredVehicles'
                }

                // Group 2: Size sorting (only one option can be selected)
                if (sortBySizeLargestToSmallest) {
                    // Implement sorting by size largest to smallest
                    // Add filtered vehicles to 'filteredVehicles'
                } else if (sortBySizeSmallestToLargest) {
                    // Implement sorting by size smallest to largest
                    // Add filtered vehicles to 'filteredVehicles'
                }

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
            //feedPanel.setMaximumSize(new Dimension(10000, Integer.MAX_VALUE));
            feedPanel.setMinimumSize(new Dimension(10000, Integer.MAX_VALUE));
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
    
        JLabel space = new JLabel("      ");
        JLabel nameLabel = new JLabel("Model/Name: " + vehicle.getMakeModel());
        ImageIcon vehicleImage = loadImageForVehicle(vehicle);
        JLabel imageLabel = new JLabel(vehicleImage);
        imageLabel.setPreferredSize(new Dimension(200, 150));
    
        JLabel seatsLabel = new JLabel("Seats: " + vehicle.getSeats());
        JLabel qualityLabel = new JLabel("Quality: " + vehicle.getQuality());
        JLabel priceLabel = new JLabel("Rental Price: $" + vehicle.getRentalPrice());
        JButton rentButton = new JButton("Rent");
        rentButton.addActionListener(rentButtonActionListener(vehicle, management));
    
        // Set maximum width for the vehicle panel
        vehiclePanel.setMinimumSize(new Dimension(5000, Integer.MAX_VALUE)); // Adjust as needed
        vehiclePanel.setMaximumSize(new Dimension(10000, Integer.MAX_VALUE)); // Adjust as needed
    
        // Add components to the panel
        vehiclePanel.add(nameLabel);
        vehiclePanel.add(imageLabel);
        vehiclePanel.add(qualityLabel);
        vehiclePanel.add(seatsLabel);
        vehiclePanel.add(priceLabel);
        vehiclePanel.add(rentButton);
        vehiclePanel.add(filterButton);
        vehiclePanel.add(space);
    
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
    public static ActionListener newVehicle() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create a new frame
                if(management.findUser(uname).isAdmin()){
                    JFrame newVehicleFrame = new JFrame();
                    JPanel formJPanel = new JPanel();

                    newVehicleFrame.add(formJPanel);
                    //should have labels and text field on the panel so that admin can enter vehicle info
                    //these contents should be defined at the top under feedScreen class
                    //a button to save vehicle info   
                    //so they can be accessed in the action listener of the saved button
                }
            }
        };
    }
    
    
    
    public ActionListener rentButtonActionListener(Vehicle vehicle, Management management) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentVehicle = vehicle;
                rentForm(management);
    
            }
        };
    }

    public void rentForm(Management management){
        JFrame rentformFrame = new JFrame();

        rentformFrame.setTitle("Vehicle Rental Service");
        rentformFrame.setSize(500, 500);
        rentformFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        rentformFrame.setLayout(new BorderLayout());

        
        JPanel rentform = new JPanel();
        BoxLayout boxlayout = new BoxLayout(rentform, BoxLayout.Y_AXIS);
        rentform.setLayout(boxlayout);
        rentform.setBorder(new EmptyBorder(new Insets(10, 30, 20, 30)));

        JLabel start_endDate = new JLabel("Start and end date: ");
        dateField1 = new JTextField(4);
        dateField2 = new JTextField(4);

        JLabel budget = new JLabel("Budget: ");
        budgetField = new JTextField(4);
        JButton button = new JButton("submit");
        button.addActionListener(buttonActionListener());



        rentformFrame.add(rentform);
        rentform.add(start_endDate);
        rentform.add(dateField1);
        rentform.add(dateField2);
        rentform.add(budget);
        rentform.add(budgetField);
        rentform.add(button);

        rentformFrame.setVisible(true);
        
        //Date date1 = dateField1.getText();
        //Date date2 = dateField2.getText();
        
}
        public ActionListener buttonActionListener() {
                    return new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date date1 = null;
                            java.util.Date date2 = null;
                            try {
                                date1 = dateFormat.parse(dateField1.getText());
                                date2 = dateFormat.parse(dateField2.getText());
                            } catch (ParseException ex) {
                            ex.printStackTrace(); // Handle parsing exception
                            System.out.println(ex);
                            }
                            int budGet = Integer.parseInt(budgetField.getText());

                            
                            
                            management.rentVehicle(date1, date2, currentVehicle);
                            //JFrame appreciation = new JFrame();
                            //appreciation.add(createAppreciationPanel());
                            //appreciation.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            createAppreciationPanel();
                        }
                    };
                }
    


    private static ImageIcon loadImageForVehicle(Vehicle vehicle) {
        String imagePath = "images/" + vehicle.getMakeModel() + ".jpg";
        System.out.println("Image path: " + imagePath);
        ImageIcon icon = new ImageIcon(imagePath);
        
        return icon;
    }

    /**
 * Creates a JPanel containing a thank you message for users who have completed a rental.
 * 
 * @return JPanel with appreciation message displayed.
 */
public static JFrame createAppreciationPanel() {
    // Create a new JPanel
    JFrame appreciationPanel = new JFrame();
    appreciationPanel.setLayout(new BorderLayout()); // Use BorderLayout for better control over layout

    // Create a JLabel with a thank you message
    JLabel thankYouLabel = new JLabel("Thank you for renting with us!", SwingConstants.CENTER);
    thankYouLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font for better visibility

    // Add the label to the panel
    appreciationPanel.add(thankYouLabel, BorderLayout.CENTER);

    return appreciationPanel;
}

}
