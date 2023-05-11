package com.challenge.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.challenge.controller.VariableController;
import com.challenge.entity.Variable;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ValorInput extends JFrame {
    private JPanel contentPane;
    public static JTextField txtValor;
    int xMouse, yMouse;
    private JLabel labelExit;
    private JLabel labelAtras;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ValorInput frame = new ValorInput();
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
    public ValorInput() {
        super("Reserva");
        setIconImage(Toolkit.getDefaultToolkit().getImage(ValorInput.class.getResource("/img/aH-40px.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 560);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.control);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);

        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 910, 560);
        contentPane.add(panel);
        panel.setLayout(null);

        // Código que crea los elementos de la interfáz gráfica
        JLabel lblTitulo = new JLabel("MODIFICAR VALOR");
        lblTitulo.setBounds(109, 60, 219, 42);
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
        panel.add(lblTitulo);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(428, 0, 482, 560);
        panel_1.setBackground(new Color(12, 138, 199));
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel logo = new JLabel("");
        logo.setBounds(197, 68, 104, 107);
        panel_1.add(logo);
        logo.setIcon(new ImageIcon(ValorInput.class.getResource("/img/Ha-100px.png")));

        JLabel imagenFondo = new JLabel("");
        imagenFondo.setBounds(0, 140, 500, 409);
        panel_1.add(imagenFondo);
        imagenFondo.setBackground(Color.WHITE);
        imagenFondo.setIcon(new ImageIcon(ValorInput.class.getResource("/img/reservas-img-3.png")));

        JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
        lblValor.setForeground(SystemColor.textInactiveText);
        lblValor.setBounds(72, 303, 196, 14);
        lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblValor);

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(SystemColor.textHighlight);
        separator_1.setBounds(68, 362, 289, 2);
        separator_1.setBackground(SystemColor.textHighlight);
        panel.add(separator_1);

        // Componentes para dejar la interfaz con estilo Material Design
        final JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario principal = new MenuUsuario();
                principal.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(new Color(12, 138, 199));
                labelExit.setForeground(Color.white);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(new Color(12, 138, 199));
        btnexit.setBounds(429, 0, 53, 36);
        panel_1.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setForeground(Color.WHITE);
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel header = new JPanel();
        header.setBounds(0, 0, 910, 36);
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
        panel.add(header);

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
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

        JLabel lblSiguiente = new JLabel("SIGUIENTE");
        lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
        lblSiguiente.setForeground(Color.WHITE);
        lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblSiguiente.setBounds(238, 493, 122, 35);
        panel.add(lblSiguiente);

        txtValor = new JTextField();
        txtValor.setBackground(SystemColor.text);
        txtValor.setHorizontalAlignment(SwingConstants.CENTER);
        txtValor.setForeground(Color.BLACK);
        txtValor.setBounds(68, 328, 289, 33);
        txtValor.setEditable(true);
        txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
        txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtValor.setColumns(10);
        panel.add(txtValor);

        JPanel btnsiguiente = new JPanel();
        btnsiguiente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!ValorInput.txtValor.getText().equals("")) {
                    guardarValor();
                    MenuUsuario menuUsuario = new MenuUsuario();
                    menuUsuario.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
                }
            }
        });
        btnsiguiente.setLayout(null);
        btnsiguiente.setBackground(SystemColor.textHighlight);
        btnsiguiente.setBounds(238, 493, 122, 35);
        panel.add(btnsiguiente);
        btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    }

    private void guardarValor() {
        Float valor;
        try {
            valor = Float.parseFloat(txtValor.getText());
        } catch (Exception e) {
            valor = 0f;
        }

        VariableController variableController = new VariableController();

        Variable variable = new Variable();
        variable.setNombre("valor");
        variable.setValorNumerico(valor);

        if (variableController.existsById("valor")) {
            variableController.update(variable);
        } else {
            variableController.save(variable);
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
