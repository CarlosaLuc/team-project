package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PetCardPanel extends JPanel {
    // expandable panel with pet details (you can expand and collapse this panel by clicking the button)
    private final JPanel detailsPanel = new JPanel(new GridLayout(0, 2, 10, 6));
    private final JButton expandButton = new JButton("+");
    private final JButton removeButton = new JButton("-");
    private boolean isExpanded = false;

    // the pet card panel consists of two main components, and I will call them:
    // 1. main row: row with image, animal type, name
    // 2. detail panel: this is the expandable panel that contains detailed info about pets

    public PetCardPanel(String petID, String name, String breed, String gender, String vaccine,
                        int age, int weight , boolean isAdmin) {

        // setup for the whole pet card (main row and expandable panel)
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new LineBorder(Color.BLACK, 1));


        // ===== 1. main row ================================================================

        // the main row of the pet card (which contains image, animal type, name)
        JPanel mainRow = new JPanel(new BorderLayout(15, 0));
        mainRow.setBackground(Color.WHITE);
        mainRow.setBorder(new EmptyBorder(12, 12, 12, 12));

        // the image will be placed here
        JLabel petImage = new JLabel("Pet Image", SwingConstants.CENTER);
        petImage.setPreferredSize(new Dimension(130, 120));
        petImage.setOpaque(true); // make the label transparent to show the background color
        petImage.setBackground(new Color(235, 238, 242));
        petImage.setBorder(new LineBorder(new Color(200, 205, 210)));

        // animal type (breed) and name
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS)); // display the texts vertically
        textPanel.setBackground(Color.WHITE);
        textPanel.setBorder(new EmptyBorder(40, 5, 10, 5));

        JLabel typeLabel = new JLabel("Animal Type: " + breed);
        typeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JLabel nameLabel = new JLabel("Name: " + name);
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        textPanel.add(typeLabel);
        textPanel.add(Box.createVerticalStrut(8)); // space between the two labels
        textPanel.add(nameLabel);

        // remove button (-) on the pet card is shown when the user is admin
        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0)); // arrange to the right side)
        rightTopPanel.setBackground(Color.WHITE);
        removeButton.setPreferredSize(new Dimension(34, 28));
        removeButton.setVisible(isAdmin); // remove button is shown only when isAdmin is true
        rightTopPanel.add(removeButton);

        mainRow.add(petImage, BorderLayout.WEST);
        mainRow.add(textPanel, BorderLayout.CENTER);
        mainRow.add(rightTopPanel, BorderLayout.EAST);


        // =====2. detail panel ==================================================================


        // expandable panel (+)
        JPanel bottomSection = new JPanel(new BorderLayout());
        bottomSection.setBackground(Color.WHITE);

        // + button for expansion
        expandButton.setBackground(new Color(225, 228, 232));
        expandButton.setOpaque(true);
        expandButton.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        expandButton.setPreferredSize(new Dimension(0, 26));

        // detailed info panel (when the panel is expanded)
        detailsPanel.setBackground(new Color(248, 249, 250));
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY),
                new EmptyBorder(10, 15, 10, 15)
        ));
        detailsPanel.add(new JLabel("Pet ID: " + petID));
        detailsPanel.add(new JLabel("Gender: " + gender));
        detailsPanel.add(new JLabel("Age: " + age + " yrs"));
        detailsPanel.add(new JLabel("Weight: " + weight + " kg"));
        detailsPanel.add(new JLabel("Vaccine: " + vaccine));
        detailsPanel.setVisible(false); // this panel is invisible at the start

        // when expand button is clicked, it calls the displayDetails (either expanding or collapsing the detail panel)
        // here, displayDetails method does not require any event info(action event), so just used actionevent for name
        expandButton.addActionListener(actionevent -> displayDetails());

        bottomSection.add(expandButton, BorderLayout.NORTH);
        bottomSection.add(detailsPanel, BorderLayout.CENTER);

        // arranging the two main components
        add(mainRow, BorderLayout.CENTER);
        add(bottomSection, BorderLayout.SOUTH);
    }

    // method that is called when expand button is clicked
    private void displayDetails() {
        isExpanded = !isExpanded; // it reverses the state (if currently isExpanded = true, then change it to isExpanded = false and vice versa)
        detailsPanel.setVisible(isExpanded);

        // this is for adjusting layout (since the card height changes as it expands and collapses)
        revalidate();
        repaint();
    }
}