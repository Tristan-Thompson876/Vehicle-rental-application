import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

public class FeedScreen extends JFrame {
    private JTable vehicleTable;
    private JTextField filterTextField;
    private JButton filterButton;
    private List<Vehicle> allVehicles; // This should be initialized with your vehicle data

    public FeedScreen(List<Vehicle> vehicles) {
        // Initialize allVehicles with the provided list
        this.allVehicles = vehicles;

        // Set up the frame
        setTitle("Vehicle Rental Service");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Navigation panel
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterTextField = new JTextField(20);
        filterButton = new JButton("Filter");
        filterButton.addActionListener(e -> filterVehicles());
        navigationPanel.add(filterTextField);
        navigationPanel.add(filterButton);

        // Vehicle display panel
        vehicleTable = new JTable();
        vehicleTable.setModel(new DefaultTableModel(new Object[]{"Make & Model", "Seats", "Rental Price", "Available"}, 0));
        JScrollPane scrollPane = new JScrollPane(vehicleTable);

        add(navigationPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void filterVehicles() {
        String searchText = filterTextField.getText().toLowerCase();
        List<Vehicle> filteredVehicles = allVehicles.stream()
                .filter(v -> v.getMakeModel().toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        updateVehicleTable(filteredVehicles);
    }

    private void updateVehicleTable(List<Vehicle> vehicles) {
        DefaultTableModel model = (DefaultTableModel) vehicleTable.getModel();
        model.setRowCount(0); // Clear existing data
        for (Vehicle v : vehicles) {
            String availability = v.isAvailable() ? "Yes" : "No";
            model.addRow(new Object[]{v.getMakeModel(), v.getSeats(), v.getRentalPrice(), availability});
        }
    }

    // Main method for demonstration purposes
    public static void main(String[] args) {
        // Here you should pass the actual list of vehicles to the FeedScreen
        new FeedScreen(/* yourListOfVehicles */);
    }
}
