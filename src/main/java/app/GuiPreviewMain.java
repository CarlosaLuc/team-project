// This is just for checking whether GUI works

package app;

import view.PetGalleryView;
import javax.swing.*;
import java.awt.*;

public class GuiPreviewMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pet Gallery GUI Testing");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(750, 620);

        PetGalleryView galleryView = new PetGalleryView();

        // this is a temporary bottom bar for testing the display for each user (Visitor, Adopter, Admin)
        JPanel roleBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        roleBar.setBackground(new Color(240, 240, 240));
        roleBar.add(new JLabel("GUI Preview Testing:"));

        JButton visitorButton = new JButton("Visitor View");
        JButton adopterButton = new JButton("Adopter View");
        JButton adminButton = new JButton("Admin View");

        // change the display based on the user role
        visitorButton.addActionListener(actionevent -> galleryView.setRole(PetGalleryView.Role.VISITOR));
        adopterButton.addActionListener(actionevent -> galleryView.setRole(PetGalleryView.Role.ADOPTER));
        adminButton.addActionListener(actionevent -> galleryView.setRole(PetGalleryView.Role.ADMIN));

        roleBar.add(visitorButton);
        roleBar.add(adopterButton);
        roleBar.add(adminButton);

        frame.setLayout(new BorderLayout());
        frame.add(galleryView, BorderLayout.CENTER);
        frame.add(roleBar, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}