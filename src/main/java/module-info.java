module org.fernando.Agenda {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens org.fernando.Agenda to javafx.fxml;
    exports org.fernando.Agenda;
}