package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class PetGalleryView extends JPanel {
    public enum Role { VISITOR, ADOPTER, ADMIN }

    private Role currentRole = Role.VISITOR; // default value

    // filter dropdown menu on the top
    private final JComboBox<String> filterDropdown = new JComboBox<>(
            new String[]{"Search Filter", "Breed", "Size", "Age", "Gender"});
    private final JButton addPetButton = new JButton("+ Add pet"); // for admin
    private final JButton portalButton = new JButton("login/signup"); // for visitors

    // the main panel that contains the pets cards, and it is scrollable.
    private final ScrollablePanel petCardsContainer = new ScrollablePanel();

    public PetGalleryView() {
        setLayout(new BorderLayout(0, 10));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(12, 16, 12, 16));

        // adding top bar (which contains filter). I created separate method for this since top bar differs for each user
        add(createTopBar(), BorderLayout.NORTH);

        // scrollable panel with cards displayed in it
        petCardsContainer.setLayout(new BoxLayout(petCardsContainer, BoxLayout.Y_AXIS));
        petCardsContainer.setBackground(Color.WHITE);
        petCardsContainer.setBorder(new EmptyBorder(8, 8, 8, 8));

        JScrollPane scrollPane = new JScrollPane(petCardsContainer);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        add(scrollPane, BorderLayout.CENTER);

        // load pet cards inside this panel
        loadPetCards();
    }

    // top bar of the gallery (different components will be shown depending on user role)
    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);
        topBar.setBorder(new EmptyBorder(0, 0, 5, 0));

        // search filter on the left top corner
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftPanel.setBackground(Color.WHITE);
        filterDropdown.setPreferredSize(new Dimension(145, 30));

        addPetButton.setPreferredSize(new Dimension(105, 30));
        addPetButton.setVisible(false); // ths button is hid when user is Visitor or Adopter

        leftPanel.add(filterDropdown);
        leftPanel.add(addPetButton);

        // portal button on the right top corner
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(portalButton);

        topBar.add(leftPanel, BorderLayout.WEST);
        topBar.add(rightPanel, BorderLayout.EAST);
        return topBar;
    }

    // change display depending on user role
    public void setRole(Role role) {
        this.currentRole = role;

        if (role == Role.VISITOR) {
            portalButton.setText("login/signup");
            addPetButton.setVisible(false);
        } else if (role == Role.ADOPTER) {
            portalButton.setText("Adopter Portal");
            addPetButton.setVisible(false);
        } else if (role == Role.ADMIN) {
            portalButton.setText("Admin Portal");
            addPetButton.setVisible(true);
        }

        // cards should be redesigned depending on user role. if user = admin, remove button should be included.
        loadPetCards();
    }

    private void loadPetCards() {
        petCardsContainer.removeAll();
        boolean isAdmin = (currentRole == Role.ADMIN);

        // these are sample data. later this should be replaced with data stored in API
        addCard(new PetCardPanel("001",  "Dog A", "Golden Retriever", "Male","Yes", 2, 28, isAdmin));
        addCard(new PetCardPanel("002",  "Dog B", "Poodle","Female", "No",1, 6,  isAdmin));
        addCard(new PetCardPanel("003",  "Dog C", "Mixed", "Male", "No", 4, 12,  isAdmin));
        addCard(new PetCardPanel("004",  "Dog D", "Husky", "Female", "Yes",3, 11,  isAdmin));
        addCard(new PetCardPanel("005",  "Dog E", "Husky", "Male", "Yes", 5, 26,  isAdmin));

        // this is for adjusting layout
        petCardsContainer.revalidate();
        petCardsContainer.repaint();
    }

    private void addCard(PetCardPanel card) {
        petCardsContainer.add(card);
        petCardsContainer.add(Box.createVerticalStrut(12)); // space between cards
    }

    // this method is for clean display. the cards inside JScrollPane resize horizontally to match the area
    private static class ScrollablePanel extends JPanel implements Scrollable {
        @Override
        public Dimension getPreferredScrollableViewportSize() { return getPreferredSize(); }
        @Override
        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) { return 16; }
        @Override
        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) { return 64; }
        @Override
        public boolean getScrollableTracksViewportWidth() { return true; }
        @Override
        public boolean getScrollableTracksViewportHeight() { return false; }
    }
}