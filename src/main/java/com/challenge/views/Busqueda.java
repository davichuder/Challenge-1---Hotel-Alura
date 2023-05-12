package com.challenge.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.challenge.controller.HuespedController;
import com.challenge.controller.ReservaController;
import com.challenge.entity.Huesped;
import com.challenge.entity.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHuesped;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;
    private ReservaController reservaController = new ReservaController();
    private HuespedController huespedController = new HuespedController();
    private TableRowSorter<DefaultTableModel> sorterReservas;
    private TableRowSorter<DefaultTableModel> sorterHuespedes;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Busqueda() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/img/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        final JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modelo = new DefaultTableModel() {
            // Hacer que la columna ID no sea editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");
        sorterReservas = new TableRowSorter<>(modelo);
        tbReservas.setRowSorter(sorterReservas);
        tbReservas.setModel(modelo);
        tbReservas.getTableHeader().setReorderingAllowed(false); // Desactivar reordenamiento de columnas
        tbReservas.setColumnSelectionAllowed(false); // Desactivar selección de columnas
        tbReservas.setCellSelectionEnabled(false); // Desactivar selección de celdas
        tbReservas.setRowSelectionAllowed(true); // Activar selección de filas
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/img/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);
        cargarReservas();

        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHuesped = new DefaultTableModel() {
            // Hacer que la columna "Número de Huesped" no sea editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        modeloHuesped.addColumn("Número de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");
        sorterHuespedes = new TableRowSorter<>(modeloHuesped);
        tbHuespedes.setRowSorter(sorterHuespedes);
        tbHuespedes.setModel(modeloHuesped);
        tbHuespedes.getTableHeader().setReorderingAllowed(false); // Desactivar reordenamiento de columnas
        tbHuespedes.setColumnSelectionAllowed(false); // Desactivar selección de columnas
        tbHuespedes.setCellSelectionEnabled(false); // Desactivar selección de celdas
        tbHuespedes.setRowSelectionAllowed(true); // Activar selección de filas
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/img/pessoas.png")), scroll_tableHuespedes,
                null);
        scroll_tableHuespedes.setVisible(true);
        cargarHuespedes();

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/img/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        final JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        final JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        final JPanel btnBuscar = new JPanel();
        btnBuscar.setLayout(null);
        btnBuscar.setBackground(new Color(12, 138, 199));
        btnBuscar.setBounds(748, 125, 122, 35);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnBuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnBuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBuscar.setBackground(new Color(0, 156, 223));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnBuscar.setBackground(SystemColor.textHighlight);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                switch (panel.getSelectedIndex()) {
                    case 0:
                        buscar(sorterReservas);
                        break;
                    case 1:
                        buscar(sorterHuespedes);
                        break;
                }
            }
        });

        JPanel btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (panel.getSelectedIndex()) {
                    case 0:
                        editarReserva();
                        break;
                    case 1:
                        editarHuesped();
                        break;
                }
            }
        });

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(500, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (panel.getSelectedIndex()) {
                    case 0:
                        eliminarReserva();
                        break;
                    case 1:
                        eliminarHuesped();
                        break;
                }
            }
        });

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);
    }

    private void cargarReservas() {
        List<Reserva> reservas = this.reservaController.list();
        for (Reserva reserva : reservas) {
            modelo.addRow(new Object[] {
                    reserva.getId(),
                    reserva.getFechaEntrada(),
                    reserva.getFechaSalida(),
                    reserva.getValor(),
                    reserva.getFormaDePago()
            });
        }
        modelo.isCellEditable(0, 0);
    }

    private void cargarHuespedes() {
        List<Huesped> huespedes = this.huespedController.list();
        for (Huesped huesped : huespedes) {
            modeloHuesped.addRow(new Object[] {
                    huesped.getId(),
                    huesped.getNombre(),
                    huesped.getApellido(),
                    huesped.getFechaDeNacimiento(),
                    huesped.getNacionalidad(),
                    huesped.getTelefono(),
                    huesped.getReserva().getId()
            });
        }
    }

    private void buscar(TableRowSorter<DefaultTableModel> tabla) {
        tabla.setRowFilter(RowFilter.regexFilter(txtBuscar.getText()));
    }

    private boolean filaSeleccionada(JTable tabla) {
        return tabla.getSelectedRowCount() != 0 || tabla.getSelectedColumnCount() != 0;
    }

    private void eliminarReserva() {
        if (filaSeleccionada(tbReservas)) {
            Object fila = modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
            if (fila != null) {
                Long id = Long.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
                this.reservaController.removeById(id);
                modelo.removeRow(tbReservas.getSelectedRow());
                JOptionPane.showMessageDialog(this, "Se eliminó correctamente.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No selecciono ninguna fila.");
        }
    }

    private void eliminarHuesped() {
        if (filaSeleccionada(tbHuespedes)) {
            Object fila = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn());
            if (fila != null) {
                Long id = Long.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                this.huespedController.removeById(id);
                modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
                JOptionPane.showMessageDialog(this, "Se eliminó correctamente.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No selecciono ninguna fila.");
        }
    }

    private void editarReserva() {
        if (filaSeleccionada(tbReservas)) {
            Object fila = modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
            if (fila != null) {
                Long id = null;
                try {
                    id = Long.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "El id debe ser numerico");
                    return;
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formatoEsperado = "\\d{4}-\\d{2}-\\d{2}";
                String fechaEnString = modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString();
                String fechaSaString = modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString();
                if (!fechaEnString.matches(formatoEsperado) || !fechaSaString.matches(formatoEsperado)) {
                    JOptionPane.showMessageDialog(this, "Las fechas debe ser yyyy-MM-dd");
                    return;
                }

                Date fechaE = null;
                Date fechaS = null;
                try {
                    fechaE = dateFormat.parse(fechaEnString);
                    fechaS = dateFormat.parse(fechaSaString);
                    System.out.println(fechaE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Las fechas debe ser yyyy-MM-dd");
                    return;
                }

                Float valor = null;
                try {
                    valor = new Float(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "El valor debe ser numerico");
                    return;
                }
                String formaPago = modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();

                Reserva reserva = new Reserva();
                reserva.setId(id);
                reserva.setFechaEntrada(fechaE);
                reserva.setFechaSalida(fechaS);
                reserva.setValor(valor);
                reserva.setFormaDePago(formaPago);
                reservaController.update(reserva);
                JOptionPane.showMessageDialog(this, "Se edito correctamente");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No selecciono ninguna fila.");
        }
    }

    private void editarHuesped() {
        if (filaSeleccionada(tbHuespedes)) {
            Object fila = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn());
            if (fila != null) {
                Long id = null;
                Integer telefono = null;
                Long reservaId = null;
                try {
                    id = Long.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "El id debe ser numerico");
                    return;
                }
                String nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
                String apellido = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formatoEsperado = "\\d{4}-\\d{2}-\\d{2}";
                String fechaNaString = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString();
                if (!fechaNaString.matches(formatoEsperado)) {
                    JOptionPane.showMessageDialog(this, "Las fechas debe ser yyyy-MM-dd");
                    return;
                }
                Date fechaN = null;
                try {
                    fechaN = dateFormat.parse(fechaNaString);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Fecha debe ser yyyy-MM-dd");
                    return;
                }

                String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
                try {
                    telefono = new Integer(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "El telefono debe ser numerico");
                    return;
                }
                try {
                    reservaId = Long.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "El id de reserva debe ser numerico");
                    return;
                }

                Huesped huesped = new Huesped();
                huesped.setId(id);
                huesped.setNombre(nombre);
                huesped.setApellido(apellido);
                huesped.setFechaDeNacimiento(fechaN);
                huesped.setNacionalidad(nacionalidad);
                huesped.setTelefono(telefono);
                Reserva reserva = reservaController.findById(reservaId);
                if (reserva != null) {
                    huesped.setReserva(reserva);
                } else {
                    JOptionPane.showMessageDialog(this, "No se ingreso una Id de reserva valido");
                    return;
                }
                this.huespedController.update(huesped);
                JOptionPane.showMessageDialog(this, "Se edito correctamente");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No seleccionó ninguna fila.");
        }
    }

    // Código que permite mover la ventana por la pantalla según la posición de "x"
    // y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}
