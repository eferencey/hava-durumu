# 🌤️ Hava Durumu Uygulaması

Java Swing kullanılarak geliştirilmiş modern ve kullanıcı dostu hava durumu uygulaması. OpenWeatherMap API'si ile gerçek zamanlı hava durumu verilerini görüntüler.

## ✨ Özellikler

- 🌍 Dünya genelinde şehir arama
- 🌡️ Anlık sıcaklık bilgisi
- 💧 Nem oranı
- 💨 Rüzgar hızı
- 📊 Atmosferik basınç
- 🎨 Modern ve şık arayüz
- 🌈 Emoji tabanlı hava durumu ikonları
- ⌨️ Enter tuşu ile hızlı arama

## 📋 Gereksinimler

- Java 8 veya üzeri
- İnternet bağlantısı
- OpenWeatherMap API anahtarı
- JSON kütüphanesi (`org.json`)

## 🚀 Kurulum

### 1. Projeyi Klonlayın
```bash
git clone https://github.com/eferencey/hava-durumu-uygulamasi.git
cd hava-durumu-uygulamasi
```

### 2. API Anahtarı Alın
1. [OpenWeatherMap](https://openweathermap.org/api) sitesine ücretsiz kayıt olun
2. API anahtarınızı alın
3. `HavaDurumuUygulamasi.java` dosyasında `API_KEY` değişkenini güncelleyin:
```java
private static final String API_KEY = "BURAYA_API_ANAHTARINIZI_YAZIN";
```

### 3. JSON Kütüphanesini Ekleyin
Maven kullanıyorsanız `pom.xml`'e ekleyin:
```xml
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20230227</version>
</dependency>
```

Manuel kurulum için JSON JAR dosyasını [buradan](https://mvnrepository.com/artifact/org.json/json) indirin.

### 4. Derleme ve Çalıştırma
```bash
javac -cp ".:json-20230227.jar" HavaDurumuUygulamasi.java
java -cp ".:json-20230227.jar" HavaDurumuUygulamasi
```

## 📱 Kullanım

1. Uygulamayı başlatın
2. Arama kutusuna şehir adını yazın
3. "Ara" butonuna tıklayın veya Enter tuşuna basın
4. Hava durumu bilgilerini görüntüleyin

## 🖼️ Ekran Görüntüleri

### Ana Ekran
```
┌─────────────────────────────────────┐
│         Hava Durumu Uygulaması      │
├─────────────────────────────────────┤
│ Şehir Adı: [İstanbul        ] [Ara] │
├─────────────────────────────────────┤
│              İstanbul, TR           │
│                                     │
│                25°C                 │
│            Açık Hava                │
│                                     │
│             Nem: 65%                │
│          Rüzgar: 12.5 km/h          │
│           Basınç: 1013 hPa          │
└─────────────────────────────────────┘
```

## 🎨 Özelleştirme

### Renk Temasını Değiştirme
`initializeGUI()` metodunda renk değerlerini değiştirin:
```java
mainPanel.setBackground(new Color(135, 206, 235)); // Açık mavi
```

### Yeni Emoji Eklemeleri
`getWeatherEmoji()` metodunu genişletin:
```java
case "fog": return "🌫️";
case "haze": return "🌫️";
// Yeni durumlar ekleyebilirsiniz
```

## 🔧 Teknik Detaylar

### Kullanılan Teknolojiler
- **Java Swing**: GUI framework
- **HttpURLConnection**: API istekleri
- **SwingWorker**: Asenkron işlemler
- **JSON**: Veri işleme

### API Endpoints
- **Base URL**: `http://api.openweathermap.org/data/2.5/weather`
- **Parameters**: 
  - `q`: Şehir adı
  - `appid`: API anahtarı
  - `units`: metric (Celsius)
  - `lang`: tr (Türkçe)

## 🐛 Bilinen Sorunlar

- Çok uzun şehir adları arayüzde taşabilir
- İnternet bağlantısı kesilirse hata mesajı gösterilir
- API limiti aşılırsa geçici erişim sorunu yaşanabilir

## 🤝 Katkıda Bulunma

1. Bu projeyi fork edin
2. Feature branch oluşturun (`git checkout -b feature/yeni-ozellik`)
3. Değişikliklerinizi commit edin (`git commit -am 'Yeni özellik eklendi'`)
4. Branch'inizi push edin (`git push origin feature/yeni-ozellik`)
5. Pull Request oluşturun

## 📝 Geliştirme Planları

- [ ] 7 günlük hava tahmini
- [ ] Grafik destekli veri görüntüleme
- [ ] Şehir favorileme sistemi
- [ ] Konum tabanlı otomatik tespit
- [ ] Dark/Light tema seçeneği
- [ ] Çoklu dil desteği

## 📄 Lisans

Bu proje MIT lisansı altında lisanslanmıştır. Detaylar için [LICENSE](LICENSE) dosyasına bakın.

## 👨‍💻 Geliştirici

**Eferencey**
- GitHub: [@eferencey](https://github.com/eferencey)
- Email: eferencyd@gmail.com

## 🙏 Teşekkürler

- [OpenWeatherMap](https://openweathermap.org/) - Hava durumu verileri için
- [Oracle](https://www.oracle.com/java/) - Java platformu için
- Emoji tasarımları için Unicode Konsorsiyumu

---

⭐ Bu projeyi beğendiyseniz yıldız vermeyi unutmayın!
