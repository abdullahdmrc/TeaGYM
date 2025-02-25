package org.example.teagym;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class GymApp extends Application {
    private Stage stage;
    Image adminImage, memberImage;
    private Scene loginScene, adminPasswordScene, memberPasswordScene, adminScene, memberScene;
    @Override
    public void start(Stage primaryStage) throws IOException {

        GymService gym = new GymService("TeaGym","Izmir");
        stage = primaryStage;

        Image image = new Image("file:gymLogin.png");    // Resmi yükle
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(700); // Genişlik 200 piksel
        imageView.setFitHeight(700); // Yükseklik 200 piksel
        imageView.setPreserveRatio(true); // Resmin oranını koru

        // Login Scene
        Button adminButton = new Button("Admin");
        Button memberButton = new Button("Member");
        HBox buttonsBox = new HBox(20, adminButton, memberButton);
        buttonsBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setLeft(imageView); // Ölçeklenmiş resmi BorderPane'e ekle
        root.setRight(buttonsBox);

        loginScene = new Scene(root, 900, 600);

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("TeaGym");
        primaryStage.show();

        Label adminPhoneLabel = new Label("Phone:");
        TextField adminPhoneField = new TextField();
        Label adminPasswordLabel = new Label("Password:");
        TextField adminPasswordField = new TextField();
        Button adminLoginButton = new Button("Login");                  //eksik
        Button adminCancelButton = new Button("Cancel");                  //eksik

        adminImage = new Image("file:gymAdmin.png");
        imageView = new ImageView(adminImage);
        imageView.setFitWidth(600); // Genişlik 200 piksel
        imageView.setFitHeight(500); // Yükseklik 200 piksel
        imageView.setPreserveRatio(true); // Resmin oranını koru

        VBox adminPasswordLayout = new VBox(20,adminPhoneLabel, adminPhoneField, adminPasswordLabel, adminPasswordField, adminLoginButton, adminCancelButton);
        adminPasswordLayout.setAlignment(Pos.CENTER);
        adminPasswordLayout.setPadding(new Insets(30));

        HBox hbox = new HBox(100, imageView, adminPasswordLayout);
        hbox.setAlignment(Pos.CENTER);

        adminPasswordScene = new Scene(hbox, 900, 600);

        memberImage = new Image("file:gymUser.png");
        imageView = new ImageView(memberImage);
        imageView.setFitWidth(700); // Genişlik 200 piksel
        imageView.setFitHeight(500); // Yükseklik 200 piksel
        imageView.setPreserveRatio(true); // Resmin oranını koru

        Label memberPhoneLabel = new Label("Phone:");
        TextField memberPhoneField = new TextField();
        Label memberPasswordLabel = new Label("Password:");
        TextField memberPasswordField = new TextField();
        Button memberLoginButton = new Button("Login");                  //eksik
        Button memberCancelButton = new Button("Cancel");                  //eksik

        VBox memberPasswordLayout = new VBox(20,memberPhoneLabel, memberPhoneField, memberPasswordLabel, memberPasswordField, memberLoginButton, memberCancelButton);
        memberPasswordLayout.setAlignment(Pos.CENTER);
        memberPasswordLayout.setPadding(new Insets(30));

        hbox = new HBox(100, imageView, memberPasswordLayout);
        hbox.setAlignment(Pos.CENTER);

        memberPasswordScene = new Scene(hbox, 900, 600);

        adminButton.setOnAction(e -> stage.setScene(adminPasswordScene));
        memberButton.setOnAction(e -> stage.setScene(memberPasswordScene));
        adminLoginButton.setOnAction(e -> showAdminScene(gym, adminPhoneField.getText().trim(), adminPasswordField.getText().trim()));
        adminCancelButton.setOnAction(e -> stage.setScene(loginScene));
        memberLoginButton.setOnAction(e -> showMemberScene(gym, memberPhoneField.getText().trim(), memberPasswordField.getText().trim()));
        memberCancelButton.setOnAction(e -> stage.setScene(loginScene));
    }
    private void showAdminScene(GymService gym, String phone, String password)
    {
        Staff staff = gym.getStaff(phone);
        if(staff == null || !staff.getPassword().equals(password)) {
            stage.setScene(adminPasswordScene);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("!!! Incorrect !!!");
            alert.setContentText("Incorrect phone number or password.");
            alert.showAndWait();
        }
        else {
            ImageView imageView = new ImageView(adminImage);
            imageView.setFitWidth(600); // Genişlik 200 piksel
            imageView.setFitHeight(500); // Yükseklik 200 piksel
            imageView.setPreserveRatio(true); // Resmin oranını koru

            // Admin Scene
            Button addMemberButton = new Button("Add Member");
            Button addStaffButton = new Button("Add Staff");
            Button addWorkScheduleButton = new Button("Add Work Schedule");
            Button addSubscriptionButton = new Button("Add Subscription");
            Button addEquipmentButton = new Button("Add Equipment");
            Button changeGymNameButton = new Button("Change Gym Name");
            Button gymInfoButton = new Button("Gym Details");                        //eksik
            Button changeMemebershipFeeListButton = new Button("Change Membership Fee List");
            Button equipmentListButton = new Button("Show All Equipments");
            Button addTrainingButton = new Button("add Training");
            Button addMeasurementButton = new Button("add Measurement");
            Button logoutAdminButton = new Button("Logout");

            VBox adminLayout = new VBox(20, addMemberButton, addStaffButton, addWorkScheduleButton, addSubscriptionButton, addEquipmentButton, gymInfoButton, addTrainingButton, addMeasurementButton, changeMemebershipFeeListButton, changeGymNameButton, equipmentListButton, logoutAdminButton);
            adminLayout.setAlignment(Pos.CENTER);
            adminLayout.setPadding(new Insets(30));

            HBox hbox = new HBox(100, imageView, adminLayout);
            hbox.setAlignment(Pos.CENTER);

            adminScene = new Scene(hbox, 900, 600);

            stage.setScene(adminScene);

            addMemberButton.setOnAction(e -> showAddMemberScene(gym));
            addStaffButton.setOnAction(e -> showAddStaffScene(gym));
            addWorkScheduleButton.setOnAction(e -> showAddScheduleScene(gym));
            addSubscriptionButton.setOnAction(e -> showAddSubscriptionScene(gym));
            addEquipmentButton.setOnAction(e -> showAddEquipmentScene(gym));
            gymInfoButton.setOnAction(e -> showViewGymDetailsAdminScene(gym));                                                           //--------------------------------
            addTrainingButton.setOnAction(e -> showAddTrainingScene(gym));
            addMeasurementButton.setOnAction(e -> showAddMeasurementScene(gym));
            changeMemebershipFeeListButton.setOnAction(e -> showAddMembershipFeeScene(gym));
            changeGymNameButton.setOnAction(e -> showChangeGymNameScene(gym));
            equipmentListButton.setOnAction(e -> showViewAllEquipmentsScene(gym));
            logoutAdminButton.setOnAction(e -> stage.setScene(loginScene));
        }
    }
    private void showMemberScene(GymService gym, String phone, String password)
    {
        Member member = gym.getMember(phone);
        if(member == null || !member.getPassword().equals(password)) {
            stage.setScene(memberPasswordScene);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("!!! Incorrect !!!");
            alert.setContentText("Incorrect phone number or password.");
            alert.showAndWait();
        }
        else {
            // User Scene
            ImageView imageView = new ImageView(memberImage);
            imageView.setFitWidth(600); // Genişlik 200 piksel
            imageView.setFitHeight(500); // Yükseklik 200 piksel
            imageView.setPreserveRatio(true); // Resmin oranını koru

            Button viewProfileButton = new Button("View Profile");
            Button viewSubscriptionDetailsButton = new Button("Subscription Details");
            Button viewMeasurementsButton = new Button("View Measurements");
            Button viewTrainingsButton = new Button("View Trainings");
            Button viewGymDetailsButton = new Button("View Gym Details");
            Button viewMembershipFeeListButton = new Button("Membership Fee List");
            Button viewCabinetButton = new Button("View Cabinet");
            Button logoutUserButton = new Button("Logout");
            VBox userLayout = new VBox(30, viewProfileButton, viewSubscriptionDetailsButton, viewMeasurementsButton, viewTrainingsButton, viewMembershipFeeListButton, viewGymDetailsButton, viewCabinetButton, logoutUserButton);
            userLayout.setAlignment(Pos.CENTER_RIGHT);
            userLayout.setPadding(new Insets(30));

            HBox hbox = new HBox(100, imageView, userLayout);
            hbox.setAlignment(Pos.CENTER);

            memberScene = new Scene(hbox, 900, 600);

            stage.setScene(memberScene);
            viewProfileButton.setOnAction(e -> showProfileScene(member));
            viewSubscriptionDetailsButton.setOnAction(e -> showSubscriptionDetailsScene(member));
            viewMeasurementsButton.setOnAction(e -> showViewMeasurementsScene(gym, member));
            viewTrainingsButton.setOnAction(e -> showViewTrainingScene(gym, member));
            viewMembershipFeeListButton.setOnAction(e -> showViewMembershipFeeListScene(gym));
            viewGymDetailsButton.setOnAction(e -> showViewGymDetailsMemberScene(gym));
            viewCabinetButton.setOnAction(e -> showViewCabinetScene(member));
            logoutUserButton.setOnAction(e -> stage.setScene(loginScene));
        }
    }
    private void showAddStaffScene(GymService gym) {
        // Add Staff Scene
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();

        Label phoneLabel = new Label("Phone:");
        TextField phoneField = new TextField();

        Label genderLabel = new Label("Gender:");
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female");


        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label staffTypeLabel = new Label("Staff type:");
        ComboBox<String> staffTypeComboBox = new ComboBox<>();
        staffTypeComboBox.getItems().addAll("Trainer", "Cleaner", "RECEPTIONIST");

        Label salaryLabel = new Label("Salary :");
        TextField salaryField = new TextField();

        Button addStaffConfirmButton = new Button("Add Staff");
        Button cancelAdminButton = new Button("Cancel");
        addStaffConfirmButton.setOnAction(e -> {
            // Add member logic here
            String name = nameField.getText().trim();
            int age = ageField.getText().trim().equals("")? 0: Integer.parseInt(ageField.getText());
            String phone = phoneField.getText().trim();
            String gender = genderComboBox.getValue();
            String password = passwordField.getText().trim();
            String staffType = staffTypeComboBox.getValue();
            double salary = ageField.getText().trim().equals("")? 0 :Double.parseDouble(salaryField.getText());
            if (phone.isEmpty()) {
                phoneField.setStyle("-fx-border-color: red");
                return;
            }
            if (password.isEmpty()) {
                passwordField.setStyle("-fx-border-color: red");
                return;
            }
            gym.addNewStaff(name, age, phone, gender, password, staffType, salary, null);
        });
        cancelAdminButton.setOnAction(e -> stage.setScene(adminScene));

        VBox addStaffLayout = new VBox(10, nameLabel, nameField, ageLabel, ageField, phoneLabel, phoneField,
                genderLabel, genderComboBox, passwordLabel, passwordField, staffTypeLabel, staffTypeComboBox, salaryLabel, salaryField,
                addStaffConfirmButton, cancelAdminButton);
        addStaffLayout.setAlignment(Pos.CENTER);
        addStaffLayout.setPadding(new Insets(30));

        ImageView imageView = setImageView(adminImage);
        HBox hbox = new HBox(150, imageView, addStaffLayout);
        hbox.setAlignment(Pos.CENTER);

        Scene addStaffScene = new Scene(hbox, 900, 600);

        stage.setScene(addStaffScene);
    }
    private void showAddScheduleScene(GymService gym)
    {
        GridPane gridPane = new GridPane();
        String[] days = {"Phone", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        TextField[] fields = new TextField[days.length];

        for (int i = 0; i < days.length; i++) {
            Label label = new Label(days[i] + ":");
            TextField textField = new TextField();
            fields[i] = textField;

            gridPane.add(label, 0, i);
            gridPane.add(textField, 1, i);
        }

        Button addScheduleButton = new Button("Add schedule");
        Button cancelAdminButton = new Button("Cancel");

        addScheduleButton.setOnAction(e -> {
            String[] works = new String[fields.length - 1];
            for (int i = 1; i < fields.length; i++) {
                works[i -1] = fields[i].getText().trim();
            }
            String phone = fields[0].getText().trim();
            if (phone.isEmpty()) {
                fields[0].setStyle("-fx-border-color: red");
                return;
            }

            gym.addWorkSchedule(phone, new WorkSchedule(works));
        });
        cancelAdminButton.setOnAction(e -> stage.setScene(adminScene));

        gridPane.add(addScheduleButton, 0, days.length);
        gridPane.add(cancelAdminButton, 1, days.length);
        gridPane.setAlignment(Pos.CENTER);

        ImageView imageView = setImageView(adminImage);
        HBox hbox = new HBox(150, imageView, gridPane);
        hbox.setAlignment(Pos.CENTER);

        Scene changeFeeListScene = new Scene(hbox, 900, 600);
        stage.setScene(changeFeeListScene);

    }
    private void showAddMeasurementScene(GymService gym)
    {
        Label phoneLabel = new Label("Phone :");
        TextField phoneField = new TextField();

        Label heightLabel = new Label("Height :");
        TextField heightField = new TextField();

        Label weightLabel = new Label("Weight :");
        TextField weightField = new TextField();

        Label fatRateLabel = new Label("Fat Rate :");
        TextField fatRateField = new TextField();
        fatRateField.setEditable(false);

        Button confirmButton = new Button("Confirm");
        Button cancelAdminButton = new Button("Cancel");
        confirmButton.setOnAction(e -> {
            double height = Double.parseDouble(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());
            double fatRate = Double.parseDouble(fatRateField.getText());
            gym.addNewMeasurement(phoneField.getText().trim(), new Measurement(height, weight, fatRate));
        });
        cancelAdminButton.setOnAction(e -> stage.setScene(adminScene));

        VBox addMeasurementLayout = new VBox(10, phoneLabel, phoneField, heightLabel, heightField, weightLabel, weightField, fatRateLabel, fatRateField, confirmButton, cancelAdminButton);
        addMeasurementLayout.setAlignment(Pos.CENTER);
        addMeasurementLayout.setPadding(new Insets(30));

        ImageView imageView = setImageView(adminImage);
        HBox hbox = new HBox(150, imageView, addMeasurementLayout);
        hbox.setAlignment(Pos.CENTER);

        Scene addMeasurementScene = new Scene(hbox, 900, 600);
        stage.setScene(addMeasurementScene);
    }
    private void showViewGymDetailsAdminScene(GymService gym)
    {
        Label viewGymDetailsLabel = new Label(gym.printGymDetailsAdmin());

        ImageView imageView = new ImageView(adminImage);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);

        Button cancelButton = new Button("Cancel");

        HBox hbox = new HBox(30, imageView, viewGymDetailsLabel, cancelButton);
        hbox.setAlignment(Pos.CENTER);

        Scene viewMeasurementsScene = new Scene(hbox, 900, 600);
        stage.setScene(viewMeasurementsScene);
        cancelButton.setOnAction(e -> stage.setScene(adminScene));
    }
    private void showViewGymDetailsMemberScene(GymService gym)
    {
        Label viewGymDetailsLabel = new Label(gym.printGymDetailsMember());

        ImageView imageView = new ImageView(memberImage);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);

        Button cancelButton = new Button("Cancel");

        HBox hbox = new HBox(30, imageView, viewGymDetailsLabel, cancelButton);
        hbox.setAlignment(Pos.CENTER);

        Scene viewMeasurementsScene = new Scene(hbox, 900, 600);
        stage.setScene(viewMeasurementsScene);
        cancelButton.setOnAction(e -> stage.setScene(memberScene));
    }
    private void showProfileScene(Member member)
    {
        String profile = String.format("Name: %s\nAge : %s\nPhone : %s\nGender : %s\nPassword : %s", member.getName(), member.getAge() == 0 ? "": member.getAge(), member.getPhoneNo(), member.getGender(), member.getPassword());
        Label profileLabel = new Label(profile);

        ImageView imageView = new ImageView(memberImage);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);

        Button cancelButton = new Button("Cancel");

        HBox hbox = new HBox(30, imageView, profileLabel, cancelButton);
        hbox.setAlignment(Pos.CENTER);

        Scene profileScene = new Scene(hbox, 900, 600);
        stage.setScene(profileScene);
        cancelButton.setOnAction(e -> stage.setScene(memberScene));
    }

    private void showSubscriptionDetailsScene(Member member)
    {
        Label subscriptionDetailsLabel = new Label(member.getSubscription().toString());

        ImageView imageView = new ImageView(memberImage);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);

        Button cancelButton = new Button("Cancel");

        HBox hbox = new HBox(30, imageView, subscriptionDetailsLabel, cancelButton);
        hbox.setAlignment(Pos.CENTER);

        Scene subscriptionDetailsScene = new Scene(hbox, 900, 600);
        stage.setScene(subscriptionDetailsScene);
        cancelButton.setOnAction(e -> stage.setScene(memberScene));
    }
    private void showViewMeasurementsScene(GymService gym,Member member)
    {
        Label viewMesumentsLabel = new Label(gym.viewMeasurements(member.getMeasurements()));

        ImageView imageView = new ImageView(memberImage);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);

        Button cancelButton = new Button("Cancel");

        HBox hbox = new HBox(30, imageView, viewMesumentsLabel, cancelButton);
        hbox.setAlignment(Pos.CENTER);

        Scene viewMeasurementsScene = new Scene(hbox, 900, 600);
        stage.setScene(viewMeasurementsScene);
        cancelButton.setOnAction(e -> stage.setScene(memberScene));
    }
    private void showViewTrainingScene(GymService gym, Member member)
    {
        Label viewTrainingLabel = new Label(gym.viewTraining(member));

        ImageView imageView = new ImageView(memberImage);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);

        Button cancelButton = new Button("Cancel");

        HBox hbox = new HBox(30, imageView, viewTrainingLabel, cancelButton);
        hbox.setAlignment(Pos.CENTER);

        Scene viewTrainingScene = new Scene(hbox, 900, 600);
        stage.setScene(viewTrainingScene);
        cancelButton.setOnAction(e -> stage.setScene(memberScene));
    }
    private void showViewCabinetScene(Member member)
    {
        Label viewCabinetLabel = new Label("Cabinet no : " + (member.getCabinetNo() == 0 ? "" : String.valueOf(member.getCabinetNo())));

        ImageView imageView = new ImageView(memberImage);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);

        Button cancelButton = new Button("Cancel");

        HBox hbox = new HBox(30, imageView, viewCabinetLabel, cancelButton);
        hbox.setAlignment(Pos.CENTER);

        Scene viewCabinetScene = new Scene(hbox, 900, 600);
        stage.setScene(viewCabinetScene);
        cancelButton.setOnAction(e -> stage.setScene(memberScene));
    }
    private void showAddMemberScene(GymService gym) {
        // Add Member Scene
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();

        Label phoneLabel = new Label("Phone:");
        TextField phoneField = new TextField();

        Label genderLabel = new Label("Gender:");
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label cabinetLabel = new Label("Cabinet:");
        PasswordField cabinetField = new PasswordField();

        Label startDateLabel = new Label("Start Date:");
        TextField startDateField = new TextField();
        startDateField.setPromptText("dd/MM/yyyy");

        Label durationLabel = new Label("Duration:");
        TextField durationField = new TextField();
        durationField.setPromptText("Number of months");

        Button addMemberConfirmButton = new Button("Add Member");
        Button cancelAdminButton = new Button("Cancel");
        addMemberConfirmButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            int age = ageField.getText().trim().equals("")? 0: Integer.parseInt(ageField.getText());
            String phone = phoneField.getText().trim();
            String gender = genderComboBox.getValue();
            String password = passwordField.getText().trim();
            int duration = durationField.getText().trim().equals("") ? 1 : Integer.parseInt(durationField.getText().trim());
            int cabinet = cabinetField.getText().trim().equals("") ? 0 : Integer.parseInt(cabinetField.getText().trim());

            String[] dates = startDateField.getText().trim().split("[./-]");

            try {
                LocalDateTime startDate = LocalDateTime.of(Integer.parseInt(dates[2].trim()), Integer.parseInt(dates[1].trim()), Integer.parseInt(dates[0].trim()), 12, 0);
            } catch (NumberFormatException | DateTimeException | IndexOutOfBoundsException ex) {
                startDateField.setStyle("-fx-border-color: red");
                return;
            }
            if (phone.isEmpty()) {
                phoneField.setStyle("-fx-border-color: red");
                return;
            }
            if (password.isEmpty()) {
                passwordField.setStyle("-fx-border-color: red");
                return;
            }
            gym.addNewMember(name, age, phone, gender, password, cabinet);
            gym.addSubscription(phone, LocalDateTime.now(), duration);
        });
        cancelAdminButton.setOnAction(e -> stage.setScene(adminScene));

        VBox addMemberLayout = new VBox(10, nameLabel, nameField, ageLabel, ageField, phoneLabel, phoneField,
                genderLabel, genderComboBox, passwordLabel, passwordField, cabinetLabel, cabinetField, startDateLabel, startDateField, durationLabel, durationField,
                addMemberConfirmButton, cancelAdminButton);
        addMemberLayout.setAlignment(Pos.CENTER);
        addMemberLayout.setPadding(new Insets(30));
        
        ImageView imageView = setImageView(adminImage);
        HBox hbox = new HBox(150, imageView, addMemberLayout);
        hbox.setAlignment(Pos.CENTER);

        Scene addMemberScene = new Scene(hbox, 900, 600);

        stage.setScene(addMemberScene);
    }
    private void showAddSubscriptionScene(GymService gym)
    {
        Label phoneLabel = new Label("Phone:");
        TextField phoneField = new TextField();

        Label startDateLabel = new Label("Start Date:");
        TextField startDateField = new TextField();
        startDateField.setPromptText("dd/MM/yyyy");

        Label durationLabel = new Label("Duration:");
        TextField durationField = new TextField();
        durationField.setPromptText("Number of months");

        Button addSubscriptionConfirmButton = new Button("Add Subscription");
        Button cancelAdminButton = new Button("Cancel");

        addSubscriptionConfirmButton.setOnAction(e ->{
            String phone = phoneField.getText().trim();
            String[] dates = startDateField.getText().trim().split("[./-]");
            int duration = durationField.getText().trim().equals("") ? 0 : Integer.parseInt(durationField.getText().trim());
            try {
                LocalDateTime startDate = LocalDateTime.of(Integer.parseInt(dates[2].trim()), Integer.parseInt(dates[1].trim()), Integer.parseInt(dates[0].trim()), 12, 0);
            } catch (NumberFormatException | DateTimeException | IndexOutOfBoundsException ex) {
                startDateField.setStyle("-fx-border-color: red");
                return;
            }

            if (phone.isEmpty()) {
                phoneField.setStyle("-fx-border-color: red");
                return;
            }
            LocalDateTime startDate = LocalDateTime.of(Integer.parseInt(dates[2].trim()), Integer.parseInt(dates[1].trim()), Integer.parseInt(dates[0].trim()), 12, 0);
            gym.addSubscription(phone, startDate, duration);
        });
        cancelAdminButton.setOnAction(e -> stage.setScene(adminScene));

        VBox addSubscriptionLayout = new VBox(10, phoneLabel, phoneField, startDateLabel, startDateField, durationLabel, durationField, addSubscriptionConfirmButton, cancelAdminButton);
        addSubscriptionLayout.setAlignment(Pos.CENTER);
        addSubscriptionLayout.setPadding(new Insets(30));

        ImageView imageView = setImageView(adminImage);
        HBox hbox = new HBox(150, imageView, addSubscriptionLayout);
        hbox.setAlignment(Pos.CENTER);

        Scene addMemberScene = new Scene(hbox, 900, 600);

        stage.setScene(addMemberScene);
    }
    private void showAddEquipmentScene(GymService gym)
    {
        Label equipmentNameLabel = new Label("Equipment Name:");
        TextField equipmentNameField = new TextField();

        Label equipmentAreaLabel = new Label("Equipment Area:");
        TextField equipmentAreaField = new TextField();

        Label quantityLabel = new Label("Quantity:");
        TextField quantityField = new TextField();

        Button addEquipmentConfirmButton = new Button("Add Equipment");
        Button cancelAdminButton = new Button("Cancel");
        addEquipmentConfirmButton.setOnAction(e -> {
            String name = equipmentNameField.getText();
            int quantity = quantityField.getText().trim().equals("") ? 1 : Integer.parseInt(quantityField.getText());;
            String[] areas = equipmentAreaField.getText().split("[,\n]");
            if (name.isEmpty()) {
                equipmentNameField.setStyle("-fx-border-color: red");
                return;
            }
            gym.addEquipment(name, quantity, areas);
        });
        cancelAdminButton.setOnAction(e -> stage.setScene(adminScene));

        VBox addEquipmentLayout = new VBox(10, equipmentNameLabel, equipmentNameField, equipmentAreaLabel, equipmentAreaField, quantityLabel, quantityField ,addEquipmentConfirmButton, cancelAdminButton);
        addEquipmentLayout.setAlignment(Pos.CENTER);
        addEquipmentLayout.setPadding(new Insets(30));

        ImageView imageView = setImageView(adminImage);
        HBox hbox = new HBox(150, imageView, addEquipmentLayout);
        hbox.setAlignment(Pos.CENTER);

        Scene addEquipmentScene = new Scene(hbox, 900, 600);
        stage.setScene(addEquipmentScene);
    }
    private void showViewAllEquipmentsScene(GymService gym)
    {
        Label viewAllEquipmentsLabel = new Label(gym.allEquipments());

        ImageView imageView = new ImageView(adminImage);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);

        Button cancelButton = new Button("Cancel");

        HBox hbox = new HBox(30, imageView, viewAllEquipmentsLabel, cancelButton);
        hbox.setAlignment(Pos.CENTER);

        Scene viewEquipmentsScene = new Scene(hbox, 900, 600);

        stage.setScene(viewEquipmentsScene);
        cancelButton.setOnAction(e -> stage.setScene(adminScene));
    }
    private void showAddMembershipFeeScene(GymService gym)
    {
        GridPane gridPane = new GridPane();
        String[] labels = {"1 months", "3 months", "6 months", "12 months"};
        TextField[] fields = new TextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i] + ":");
            TextField textField = new TextField();
            fields[i] = textField;

            gridPane.add(label, 0, i);
            gridPane.add(textField, 1, i);
        }

        Button addMembershipFeeButton = new Button("Change membership fee");
        Button cancelAdminButton = new Button("Cancel");

        addMembershipFeeButton.setOnAction(e -> {
            double[] fees = new double[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fees[i] = Double.parseDouble(fields[i].getText().trim());
            }
            gym.changeMembershipFeeList(fees);
        });
        cancelAdminButton.setOnAction(e -> stage.setScene(adminScene));

        gridPane.add(addMembershipFeeButton, 0, labels.length);
        gridPane.add(cancelAdminButton, 1, labels.length);
        gridPane.setAlignment(Pos.CENTER);

        ImageView imageView = setImageView(adminImage);
        HBox hbox = new HBox(150, imageView, gridPane);
        hbox.setAlignment(Pos.CENTER);

        Scene changeFeeListScene = new Scene(hbox, 900, 600);
        stage.setScene(changeFeeListScene);
    }
    private void showViewMembershipFeeListScene(GymService gym)
    {
        Label viewMemebershipFeeListLabel = new Label(gym.membershipFeeList());

        ImageView imageView = new ImageView(memberImage);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);

        Button cancelButton = new Button("Cancel");

        HBox hbox = new HBox(30, imageView, viewMemebershipFeeListLabel, cancelButton);
        hbox.setAlignment(Pos.CENTER);

        Scene viewMembershipFeeListScene = new Scene(hbox, 900, 600);

        stage.setScene(viewMembershipFeeListScene);
        cancelButton.setOnAction(e -> stage.setScene(memberScene));
    }
    private void showAddTrainingScene(GymService gym)
    {
        GridPane gridPane = new GridPane();
        String[] days = {"Phone", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        TextField[] fields = new TextField[days.length];

        for (int i = 0; i < days.length; i++) {
            Label label = new Label(days[i] + ":");
            TextField textField = new TextField();
            fields[i] = textField;

            gridPane.add(label, 0, i);
            gridPane.add(textField, 1, i);
        }

        Button addTrainingConfirmButton = new Button("Add Training");
        Button cancelAdminButton = new Button("Cancel");

        addTrainingConfirmButton.setOnAction(e -> {
            String phone = fields[0].getText().trim();
            for (int i = 1; i < fields.length; i++) {
                gym.setDailyTraining(phone, i - 1, fields[i].getText().trim().replaceAll(",", " -"));
            }

        });
        cancelAdminButton.setOnAction(e -> stage.setScene(adminScene));

        gridPane.add(addTrainingConfirmButton, 0, days.length);
        gridPane.add(cancelAdminButton, 1, days.length);
        gridPane.setAlignment(Pos.CENTER);

        ImageView imageView = setImageView(adminImage);
        HBox hbox = new HBox(150, imageView, gridPane);
        hbox.setAlignment(Pos.CENTER);

        Scene addTrainingScene = new Scene(hbox, 900, 600);
        stage.setScene(addTrainingScene);
    }

    private void showChangeGymNameScene(GymService gym)
    {
        Label nameLabel = new Label("Gym Name:");
        TextField nameField = new TextField();

        Button confirmButton = new Button("Change Gym Name");
        Button cancelAdminButton = new Button("Cancel");
        confirmButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                nameField.setStyle("-fx-border-color: red");
                return;
            }
            gym.setGymName(name);
        });
        cancelAdminButton.setOnAction(e -> stage.setScene(adminScene));

        VBox changeGymNameLayout = new VBox(10, nameLabel, nameField, confirmButton, cancelAdminButton);
        changeGymNameLayout.setAlignment(Pos.CENTER);
        changeGymNameLayout.setPadding(new Insets(30));

        ImageView imageView = setImageView(adminImage);
        HBox hbox = new HBox(150, imageView, changeGymNameLayout);
        hbox.setAlignment(Pos.CENTER);

        Scene changeGymNameScene = new Scene(hbox, 900, 600);
        stage.setScene(changeGymNameScene);
    }
    private ImageView setImageView(Image image)
    {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300); // Genişlik 200 piksel
        imageView.setFitHeight(500); // Yükseklik 200 piksel
        imageView.setPreserveRatio(true); // Resmin oranını koru
        return imageView;
    }
    public static void main(String[] args) {

        launch(args);
    }
}