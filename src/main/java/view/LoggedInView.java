package view;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.add_pet.AddPetViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.navigation.NavigationController;

/**
 * Placeholder shown after logging in. Replace with the adopter home page
 * (browse pets) or the admin dashboard when those parts are ready.
 * Log out is handled by another teammate.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {
    private final JLabel welcomeLabel = FormUtils.title("Welcome!");
    private final JButton addPetButton = new JButton("Add a pet");

    public LoggedInView(LoggedInViewModel viewModel, NavigationController navigationController) {
        setLayout(new BorderLayout());
        setName(LoggedInViewModel.VIEW_NAME);
        viewModel.addPropertyChangeListener(this);

        // Only the admin can add pets.
        addPetButton.setVisible(false);
        addPetButton.addActionListener(e -> navigationController.goTo(AddPetViewModel.VIEW_NAME));

        final JPanel content = FormUtils.verticalPanel();
        content.add(welcomeLabel);
        content.add(FormUtils.centered(addPetButton));
        add(content, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoggedInState state = (LoggedInState) evt.getNewValue();
        welcomeLabel.setText("Welcome, " + state.getUsername() + " (" + state.getRole() + ")");
        addPetButton.setVisible(LoggedInState.ROLE_ADMIN.equals(state.getRole()));
    }
}
