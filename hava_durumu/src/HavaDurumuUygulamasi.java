import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;


public class HavaDurumuUygulamasi extends JFrame {
    
    
    private static final String API_KEY = "0c32eb4e9e848ba27b1dcfa40284b53f";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    
    // GUI bileşenleri
    private JTextField sehirField;
    private JButton araButton;
    private JLabel sehirLabel;
    private JLabel sicaklikLabel;
    private JLabel durumLabel;
    private JLabel nemLabel;
    private JLabel ruzgarLabel;
    private JLabel basincLabel;
    private JLabel iconLabel;
    
    public HavaDurumuUygulamasi() {
        initializeGUI();
    }
    
    private void initializeGUI() {
        // Ana pencere ayarları
        setTitle("Hava Durumu Uygulaması");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Ana panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(135, 206, 235)); // Açık mavi
        
        // Üst panel - Arama
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(new Color(135, 206, 235));
        
        JLabel label = new JLabel("Şehir Adı:");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        sehirField = new JTextField(15);
        araButton = new JButton("Ara");
        araButton.setBackground(new Color(70, 130, 180));
        araButton.setForeground(Color.WHITE);
        araButton.setFont(new Font("Arial", Font.BOLD, 12));
        
        topPanel.add(label);
        topPanel.add(sehirField);
        topPanel.add(araButton);
        
        // Orta panel - Hava durumu bilgileri
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(135, 206, 235));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Şehir adı
        sehirLabel = new JLabel("Şehir seçiniz");
        sehirLabel.setFont(new Font("Arial", Font.BOLD, 24));
        sehirLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sehirLabel.setForeground(Color.WHITE);
        
        // İcon için label (emoji kullanacağız)
        iconLabel = new JLabel("🌤️");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Sıcaklık
        sicaklikLabel = new JLabel("--°C");
        sicaklikLabel.setFont(new Font("Arial", Font.BOLD, 48));
        sicaklikLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sicaklikLabel.setForeground(Color.WHITE);
        
        // Durum
        durumLabel = new JLabel("Hava durumu");
        durumLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        durumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        durumLabel.setForeground(Color.WHITE);
        
        // Detay bilgileri
        JPanel detayPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        detayPanel.setBackground(new Color(135, 206, 235));
        detayPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        nemLabel = new JLabel("Nem: --%");
        nemLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nemLabel.setForeground(Color.WHITE);
        nemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        ruzgarLabel = new JLabel("Rüzgar: -- km/h");
        ruzgarLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        ruzgarLabel.setForeground(Color.WHITE);
        ruzgarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        basincLabel = new JLabel("Basınç: -- hPa");
        basincLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        basincLabel.setForeground(Color.WHITE);
        basincLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        detayPanel.add(nemLabel);
        detayPanel.add(ruzgarLabel);
        detayPanel.add(basincLabel);
        
        // Bileşenleri center panele ekleme
        centerPanel.add(sehirLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(iconLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(sicaklikLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(durumLabel);
        centerPanel.add(detayPanel);
        
        // Panelleri ana panele ekleme
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        
        // Event listener'lar
        araButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sehir = sehirField.getText().trim();
                if (!sehir.isEmpty()) {
                    havaDurumuGetir(sehir);
                } else {
                    JOptionPane.showMessageDialog(HavaDurumuUygulamasi.this, 
                        "Lütfen bir şehir adı giriniz!", "Hata", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        // Enter tuşu ile arama
        sehirField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                araButton.doClick();
            }
        });
    }
    
    private void havaDurumuGetir(String sehir) {
        // API anahtarı kontrolü
        if (API_KEY.equals("YOUR_API_KEY_HERE")) {
            JOptionPane.showMessageDialog(this, 
                "API anahtarını ayarlamanız gerekiyor!\n" +
                "OpenWeatherMap'ten ücretsiz API anahtarı alabilirsiniz.",
                "API Anahtarı Gerekli", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        SwingWorker<JSONObject, Void> worker = new SwingWorker<JSONObject, Void>() {
            @Override
            protected JSONObject doInBackground() throws Exception {
                String urlString = BASE_URL + "?q=" + sehir + "&appid=" + API_KEY + "&units=metric&lang=tr";
                URL url = new URL(urlString);
                
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                
                reader.close();
                connection.disconnect();
                
                return new JSONObject(response.toString());
            }
            
            @Override
            protected void done() {
                try {
                    JSONObject data = get();
                    guncelleBilgileri(data);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(HavaDurumuUygulamasi.this, 
                        "Hava durumu bilgisi alınamadı!\n" +
                        "Şehir adını kontrol ediniz veya internet bağlantınızı kontrol ediniz.",
                        "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        
        worker.execute();
    }
    
    private void guncelleBilgileri(JSONObject data) {
        try {
            // Şehir adı
            String sehirAdi = data.getString("name");
            String ulke = data.getJSONObject("sys").getString("country");
            sehirLabel.setText(sehirAdi + ", " + ulke);
            
            // Sıcaklık
            int sicaklik = (int) Math.round(data.getJSONObject("main").getDouble("temp"));
            sicaklikLabel.setText(sicaklik + "°C");
            
            // Hava durumu açıklaması
            String aciklama = data.getJSONArray("weather").getJSONObject(0).getString("description");
            durumLabel.setText(aciklama.substring(0, 1).toUpperCase() + aciklama.substring(1));
            
            // Hava durumu ikonu (emoji)
            String weatherMain = data.getJSONArray("weather").getJSONObject(0).getString("main");
            iconLabel.setText(getWeatherEmoji(weatherMain));
            
            // Detay bilgileri
            int nem = data.getJSONObject("main").getInt("humidity");
            double ruzgarHizi = data.getJSONObject("wind").getDouble("speed") * 3.6; // m/s'den km/h'ye
            int basinc = data.getJSONObject("main").getInt("pressure");
            
            nemLabel.setText("Nem: " + nem + "%");
            ruzgarLabel.setText("Rüzgar: " + String.format("%.1f", ruzgarHizi) + " km/h");
            basincLabel.setText("Basınç: " + basinc + " hPa");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Veri işlenirken hata oluştu!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String getWeatherEmoji(String weatherMain) {
        switch (weatherMain.toLowerCase()) {
            case "clear": return "☀️";
            case "clouds": return "☁️";
            case "rain": return "🌧️";
            case "thunderstorm": return "⛈️";
            case "snow": return "❄️";
            case "drizzle": return "🌦️";
            case "mist":
            case "fog": return "🌫️";
            default: return "🌤️";
        }
    }
    
    public static void main(String[] args) {
        // Look and Feel ayarlama
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HavaDurumuUygulamasi().setVisible(true);
            }
        });
    }
}