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


/**
 * The FeedScreen class provides a graphical user interface for displaying and managing vehicle rentals.
 * It allows users to filter, rent, and add new vehicles to the system.
 */

public class FeedScreen extends JFrame {
    private static String uname;
    private static Management management;
    private User currentUser;
    private JTable vehicleTable;
    private JTextField filterTextField;
    private JButton filterButton;
    private JButton newVehicleButton;
    private JButton editVehicleButton;
    private JTextField dateField1;
    private JTextField dateField2;
    JTextField budgetField;
    private Vehicle currentVehicle;
    private JPanel navigationPanel = new JPanel();
    private JPanel filterPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    JPanel feedPanel = new JPanel();

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
     /**
     * Constructs the FeedScreen which initializes the user interface components and sets up event listeners.
     *
     * @param uname The username of the current user.
     * @param frame The parent frame.
     * @param management The management system handling vehicle and user operations.
     */
    public FeedScreen(String uname, JFrame frame, Management management) {
        this.uname = uname;
        this.frame = frame;
        this.management = management;
        this.vehicles = management.getVehicles();
        this.currentUser = management.findUser(uname);

       

        // Set up the frame
        setTitle("Vehicle Rental Service");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        

    
        
        JLabel titleLabel = new JLabel("Vehicles");
        JPanel mainPanel = new JPanel();
       
        navigationPanel.setLayout(new GridLayout(5,2));
        //navigationPanel.add(titleLabel);
       
        
        feedPanel.setLayout(new GridLayout(0,2)); 
        
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

        navigationPanel.add(buttonPanel);
        navigationPanel.setBackground(Color.GRAY);
        buttonPanel.setBackground(Color.GRAY);
        priceHighToLowCheckbox.setBackground(Color.GRAY);
        priceLowToHighCheckbox.setBackground(Color.GRAY);
        sizeLargestToSmallestCheckbox.setBackground(Color.GRAY);
        sizeSmallestToLargestCheckbox.setBackground(Color.GRAY);

        navigationPanel.setLayout(new GridLayout(5, 2));
        //filterTextField = new JTextField(20);
        filterButton = new JButton("Filter");
        newVehicleButton = new JButton("New");
        editVehicleButton = new JButton("Edit");
        

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
        showAllVehicles(filterVehicles(), feedPanel);
        

        System.out.println("baii");

        JScrollPane scrollPane = new JScrollPane(feedPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        //feedPanel.add(scrollPane, BorderLayout.CENTER);

        //filterTextField = new JTextField(20);
        //filterButton = new JButton("Filter");
        filterButton.addActionListener(filterListener());
        newVehicleButton.addActionListener(newVehicle(this, currentUser));
        editVehicleButton.addActionListener(editVehicleListener());

        //mainPanel.add(navigationPanel);
        //navigationPanel.add(filterTextField);
        buttonPanel.add(filterButton);
        buttonPanel.add(newVehicleButton);
        buttonPanel.add(editVehicleButton);
        //mainPanel.add(feedPanel);

        add(navigationPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(scrollPane);
        setVisible(true);
    }


    
    private ActionListener editVehicleListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vehicle v = management.findVehicle(currentVehicle);

    
            }
        };
    }



    private ArrayList<Vehicle> filterVehicles() {
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();
                //navigationPanel.add(filterPanel);
                //filterButton.setEnabled(true);
                navigationPanel.add(filterButton);
                //filterPanel.removeAll();


                if (!priceHighToLowCheckbox.isSelected() &&
                !priceLowToHighCheckbox.isSelected() &&
                !sizeLargestToSmallestCheckbox.isSelected() &&
                !sizeSmallestToLargestCheckbox.isSelected()) {
                    return vehicles;
                }
                else{

                boolean sortByPriceHighToLow = priceHighToLowCheckbox.isSelected();
                boolean sortByPriceLowToHigh = priceLowToHighCheckbox.isSelected();
                boolean sortBySizeLargestToSmallest = sizeLargestToSmallestCheckbox.isSelected();
                boolean sortBySizeSmallestToLargest = sizeSmallestToLargestCheckbox.isSelected();

                // Group 2: Size sorting (only one option can be selected)
                if (sortBySizeLargestToSmallest) {
                    ArrayList<Vehicle> vs = management.sortBySizeLargestSmall();
                    filteredVehicles = vs;
                    // Add filtered vehicles to 'filteredVehicles'
                } else if (sortBySizeSmallestToLargest) {
                    ArrayList<Vehicle> vs = management.sortBySizeSmallLargest();
                    filteredVehicles = vs;
                    // Add filtered vehicles to 'filteredVehicles'
                }

                if (sortByPriceHighToLow) {
                    ArrayList<Vehicle> vs = management.sortByPriceHighLow();
                    // Add filtered vehicles to 'filteredVehicles'
                    filteredVehicles = vs;
                } else if (sortByPriceLowToHigh) {
                    ArrayList<Vehicle> vs = management.sortByPriceLowHigh();
                    filteredVehicles = vs;
                    // Add filtered vehicles to 'filteredVehicles'
                }

                return filteredVehicles;
            }
    }
     /**
     * Creates an ActionListener that filters vehicles when the filter button is pressed.
     *
     * @return An ActionListener that updates the vehicle display based on filters.
     */
    public ActionListener filterListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Vehicle> filtered = filterVehicles();
                showAllVehicles(filtered, feedPanel);
                filterButton.setEnabled(true);
                buttonPanel.add(filterButton);
            }
        };
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

    
    /**
     * Displays all vehicles in the feed panel.
     *
     * @param vehicles The list of vehicles to display.
     * @param feedPanel The panel where vehicles are displayed.
     */
    public void showAllVehicles(ArrayList<Vehicle> vehicles, JPanel feedPanel) {
        
        if(!filterVehicles().isEmpty()){
            System.out.println("lets see");
            //JScrollPane scrollPane = new JScrollPane(feedPanel);
            feedPanel.removeAll();
            GridLayout gridLayout = new GridLayout(3, 2);
            feedPanel.setLayout(gridLayout);
            //feedPanel.setMaximumSize(new Dimension(10000, Integer.MAX_VALUE));
            feedPanel.setMinimumSize(new Dimension(10000, Integer.MAX_VALUE));
            //feedPanel.add(scrollPane);
            for (Vehicle v : filterVehicles()) {
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
      /**
     * Creates a panel for a single vehicle, showing details and an option to rent.
     *
     * @param vehicle The vehicle to create a panel for.
     * @return The JPanel representing the vehicle.
     */
    public JPanel createVehiclePanel(Vehicle vehicle) {
        JPanel vehiclePanel = new JPanel();
        vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.Y_AXIS)); // Vertical layout
    
        JLabel space = new JLabel("      ");
        JLabel nameLabel = new JLabel("Model/Name: " + vehicle.getMakeModel());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
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

    /* 
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
    }*/

    
    public static ActionListener newVehicle(FeedScreen feedScreen, User currentUser) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create a new frame
                if (management.findAdmin(currentUser.getName())) {
                    JFrame newVehicleFrame = new JFrame();
                    JPanel formJPanel = new JPanel();
                    newVehicleFrame.setTitle("Add New Vehicle");
                    newVehicleFrame.setSize(400, 300);
    
                    JLabel makeModelLabel = new JLabel("Make/Model:");
                    JTextField makeModelField = new JTextField(20);
                    JLabel qualityLabel = new JLabel("Quality:");
                    JTextField qualityField = new JTextField(20);
                    JLabel seatsLabel = new JLabel("Seats:");
                    JTextField seatsField = new JTextField(20);
                    JLabel rentalPriceLabel = new JLabel("Rental Price:");
                    JTextField rentalPriceField = new JTextField(20);
                    JLabel availableLabel = new JLabel("Available:");
                    JTextField availableField = new JTextField(20);
    
                    JButton saveButton = new JButton("Save");
    
                    formJPanel.add(makeModelLabel);
                    formJPanel.add(makeModelField);
                    formJPanel.add(qualityLabel);
                    formJPanel.add(qualityField);
                    formJPanel.add(seatsLabel);
                    formJPanel.add(seatsField);
                    formJPanel.add(rentalPriceLabel);
                    formJPanel.add(rentalPriceField);
                    formJPanel.add(availableLabel);
                    formJPanel.add(availableField);
                    formJPanel.add(saveButton);
    
                    newVehicleFrame.add(formJPanel);
                    newVehicleFrame.setVisible(true);
    
                    saveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Retrieve entered data and save it
                            String makeModel = makeModelField.getText();
                            String quality = qualityField.getText();
                            int seats = Integer.parseInt(seatsField.getText());
                            int rentalPrice = Integer.parseInt(rentalPriceField.getText());
                            boolean available = Boolean.parseBoolean(availableField.getText());
    
                            // Call method to save vehicle info through the feedScreen instance
                            feedScreen.saveVehicleInfo(makeModel, quality, seats, rentalPrice, available);
    
                            // Close the frame after saving
                            newVehicleFrame.dispose();
                            
                        }
                    });
                }
            }
        };
    }
    
    
    


    // Method to save vehicle information to the ArrayList
    public void saveVehicleInfo(String makeModel, String quality, int seats, int rentalPrice, boolean available) {
        // Create a new Vehicle object
        Vehicle newVehicle = new Vehicle(makeModel, quality, seats, rentalPrice, available);
        
        // Add the new vehicle to the ArrayList
        vehicles.add(newVehicle);
        
        // Print confirmation message
        System.out.println("Vehicle information saved successfully:");
        System.out.println(newVehicle);
    }
    
    
    
    
    public ActionListener rentButtonActionListener(Vehicle vehicle, Management management) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentVehicle = vehicle;
                rentForm(management, vehicle);
    
            }
        };
    }
    //

    public void rentForm(Management management, Vehicle vehicle){
        currentVehicle = vehicle;
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

                            
                            
                            String s = management.rentVehicle(date1, date2, currentVehicle, budGet);
                            //JFrame appreciation = new JFrame();
                            //appreciation.add(createAppreciationPanel());
                            //appreciation.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            createAppreciationPanel(currentVehicle, s );
                        }
                    };
                }
    

    /**
     * Loads an image icon for a given vehicle based on its make and model.
     *
     * @param vehicle The vehicle for which the image should be loaded.
     * @return ImageIcon representing the vehicle.
     */
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
public static JFrame createAppreciationPanel(Vehicle vehicle, String s) {
    
    System.out.println("here");
    JFrame appreciationFrame = new JFrame();
    JPanel panel = new JPanel();
    appreciationFrame.setLayout(new BorderLayout()); 

    
    JLabel confirmation = new JLabel(s);
    JLabel thankYouLabel = new JLabel("Thank you for renting with us!", SwingConstants.CENTER);
    thankYouLabel.setFont(new Font("Arial", Font.BOLD, 16)); 

    // Add the label to the panel
    panel.add(confirmation);
    panel.add(thankYouLabel, BorderLayout.CENTER);

    appreciationFrame.add(panel);
    appreciationFrame.setSize(400, 300); 
    appreciationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    appreciationFrame.setVisible(true); 

    return appreciationFrame;
}

}

//}