package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import interface_adapter.add_pet.AddPetController;
import interface_adapter.add_pet.AddPetState;
import interface_adapter.add_pet.AddPetViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.navigation.NavigationController;

/**
 * "Add a pet" screen for the admin: pet details, an optional picture, and an Add button.
 */
public class AddPetView extends JPanel implements PropertyChangeListener {
    private static final String CHOOSE_GENDER = "-- choose --";
    private static final int PREVIEW_SIZE = 140;

    private final JTextField petIdField = new JTextField(15);
    private final JTextField nameField = new JTextField(15);
    private final JTextField breedField = new JTextField(15);
    private final JComboBox<String> genderBox = new JComboBox<>(new String[] {CHOOSE_GENDER, "Male", "Female"});
    private final JCheckBox vaccinatedBox = new JCheckBox("Vaccinated");
    private final JTextField ageField = new JTextField(15);
    private final JTextField weightField = new JTextField(15);
    private final JLabel imagePreview = new JLabel("No image", SwingConstants.CENTER);
    private final JLabel messageLabel = FormUtils.messageLabel();
    private String imagePath = "";

    public AddPetView(AddPetViewModel viewModel,
                      AddPetController controller,
                      NavigationController navigationController) {
        setLayout(new BorderLayout());
        setName(AddPetViewModel.VIEW_NAME);
        viewModel.addPropertyChangeListener(this);

        imagePreview.setPreferredSize(new Dimension(PREVIEW_SIZE, PREVIEW_SIZE));
        imagePreview.setBorder(BorderFactory.createEtchedBorder());

        final JButton chooseImageButton = new JButton("Choose image...");
        final JButton addButton = new JButton("Add");
        final JButton backButton = new JButton("Back");

        chooseImageButton.addActionListener(e -> chooseImage());
        addButton.addActionListener(e -> controller.execute(
                petIdField.getText(),
                nameField.getText(),
                breedField.getText(),
                CHOOSE_GENDER.equals(genderBox.getSelectedItem()) ? "" : (String) genderBox.getSelectedItem(),
                vaccinatedBox.isSelected(),
                ageField.getText(),
                weightField.getText(),
                imagePath));
        backButton.addActionListener(e -> {
            clearForm();
            messageLabel.setText(" ");
            navigationController.goTo(LoggedInViewModel.VIEW_NAME);
        });

        final JPanel content = FormUtils.verticalPanel();
        content.add(FormUtils.title("Add a pet"));
        content.add(FormUtils.row("Pet ID:", petIdField));
        content.add(FormUtils.row("Name:", nameField));
        content.add(FormUtils.row("Breed:", breedField));
        content.add(FormUtils.row("Gender:", genderBox));
        content.add(FormUtils.centered(vaccinatedBox));
        content.add(FormUtils.row("Age (years):", ageField));
        content.add(FormUtils.row("Weight (kg):", weightField));
        content.add(FormUtils.centered(imagePreview));
        content.add(FormUtils.centered(chooseImageButton));
        content.add(messageLabel);
        content.add(FormUtils.centered(addButton));
        content.add(FormUtils.centered(backButton));

        final JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }

    private void chooseImage() {
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            setImage(chooser.getSelectedFile());
        }
    }

    private void setImage(File file) {
        imagePath = file.getAbsolutePath();
        final Image scaled = new ImageIcon(imagePath).getImage()
                .getScaledInstance(PREVIEW_SIZE, PREVIEW_SIZE, Image.SCALE_SMOOTH);
        imagePreview.setText("");
        imagePreview.setIcon(new ImageIcon(scaled));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AddPetState state = (AddPetState) evt.getNewValue();
        if (state.getError() == null) {
            // The pet was added: empty the form for the next one.
            clearForm();
        }
        FormUtils.showMessage(messageLabel, state.getError(), state.getNotice());
    }

    private void clearForm() {
        petIdField.setText("");
        nameField.setText("");
        breedField.setText("");
        genderBox.setSelectedIndex(0);
        vaccinatedBox.setSelected(false);
        ageField.setText("");
        weightField.setText("");
        imagePath = "";
        imagePreview.setIcon(null);
        imagePreview.setText("No image");
    }
}
