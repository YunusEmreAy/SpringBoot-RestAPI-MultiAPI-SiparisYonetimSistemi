# Sipariş Yönetim Sistemi

Bu proje, **Sipariş Yönetim Sistemi**ni kapsayan bir Spring Boot uygulamasıdır. Kullanıcılar sipariş oluşturabilir, ürünler ve üyeler yönetilebilir. Proje, **iki farklı API** içermektedir:

1. **Sipariş Yönetim API'si (API1)**: Sipariş, Üye ve Ürün işlemlerini yönetir. **(Port: 9090)**
2. **Banka API'si (API2)**: Kullanıcıların bakiye işlemlerini yönetir. **(Port: 9091)**

---

## Proje İçeriği

### 1. Modeller (Entities)

Proje, dört ana veritabanı modelinden oluşmaktadır:

- **Uye.java**: Kullanıcı (Üye) bilgilerini içeren entity. Üyeler, sipariş oluşturabilir ve banka bakiyesi ile ödeme yapabilir.
  - `id`: Üyenin benzersiz kimliği.
  - `firstName`: Üyenin adı.
  - `lastName`: Üyenin soyadı.
  - `tcKimlikNo`: Üyenin TC Kimlik Numarası.
  - `siparis`: Üyenin yaptığı siparişler.

- **Urun.java**: Ürün bilgilerini içeren entity. Kullanıcılar bu ürünleri sipariş edebilir.
  - `id`: Ürünün benzersiz kimliği.
  - `name`: Ürünün adı.
  - `stok`: Mevcut stok miktarı.
  - `fiyat`: Ürünün fiyatı.
  - `siparis`: Ürünle ilişkilendirilen siparişler.

- **Siparis.java**: Sipariş bilgilerini içeren entity. Kullanıcılar belirli bir ürün için sipariş oluşturabilir.
  - `id`: Siparişin benzersiz kimliği.
  - `date`: Siparişin oluşturulma tarihi.
  - `uye`: Siparişi veren üye.
  - `urun`: Sipariş edilen ürün.
  - `adet`: Sipariş edilen ürün adedi.
  - `siparisTutari`: Siparişin toplam tutarı.

- **Banka.java**: Kullanıcıların bakiyelerini yöneten entity. Sipariş işlemlerinde ödeme doğrulaması yapılmasını sağlar.
  - `tcKimlikNo`: Kullanıcının TC Kimlik Numarası.
  - `bakiye`: Kullanıcının mevcut bakiyesi.

---

## 2 Farklı API Yapısı

Proje, **iki ayrı API** üzerinden çalışmaktadır:

1. **Sipariş Yönetim API'si (API1)** - `server.port=9090`
   - Üyeleri, ürünleri ve siparişleri yönetir.
   - PostgreSQL veritabanında **API1** şeması ile çalışır.
   - Örnek URL: `http://localhost:9090/api/siparis`

2. **Banka API'si (API2)** - `server.port=9091`
   - Kullanıcıların banka bakiyesini yönetir.
   - PostgreSQL veritabanında **API2** şeması ile çalışır.
   - Örnek URL: `http://localhost:9091/api/banka`

---

## Yeni Kayıt Oluşturma

**1. Yeni Üye Eklemek İçin:**
```sh
POST http://localhost:9090/rest/api/uye
Content-Type: application/json
{
  "firstName": "Ahmet",
  "lastName": "Yılmaz",
  "tcKimlikNo": 12345678901
}
```

**2. Yeni Ürün Eklemek İçin:**
```sh
POST http://localhost:9090/rest/api/urun
Content-Type: application/json
{
  "name": "Laptop",
  "stok": 20,
  "fiyat": 15000
}
```

**3. Bankaya Yeni Kişi Eklemek İçin:**
```sh
POST http://localhost:9091/rest/api/banka
Content-Type: application/json
{
  "tcKimlikNo": 12345678901,
  "bakiye": 50000
}
```

**4. Yeni Sipariş Oluşturmak İçin:**
```sh
POST http://localhost:9090/rest/api/siparis
Content-Type: application/json
{
  "uye_id": 1,
  "urun_id": 2,
  "adet": 3
}
```

---

## API1'in API2'yi Çağırarak İşlemleri Gerçekleştirmesi

Sipariş oluşturulduğunda, **API1 otomatik olarak API2'yi çağırarak** müşterinin bakiye kontrolünü yapar ve bakiyeyi günceller.

1. **Müşterinin Bakiyesi Yeterli mi?**
   - API1, API2'ye bir HTTP isteği göndererek üyenin bakiyesinin sipariş tutarını karşılayıp karşılamadığını kontrol eder.
   - `http://localhost:9091/rest/api/banka/bakiyeSorgula/{tcKimlikNo}/{siparisTutari}`
   - Eğer bakiye yeterliyse, bakiye API2 tarafından düşülür.

2. **Sipariş Başarılıysa Stok Güncellenir**
   - API1, ürünü veritabanında güncelleyerek stoktan düşürür.
   - Eğer stok yetersizse, sipariş oluşturulmaz.

3. **Ödeme Başarısızsa İşlem Gerçekleşmez**
   - Eğer API2'den **"Müşteri Bakiyesi Yetersiz!"** yanıtı gelirse, sipariş işlemi iptal edilir.

**API1'in API2'yi Çağırdığı Örnek Kod:**
```java
HttpResponse<String> response = Unirest.get("http://localhost:9091/rest/api/banka/bakiyeSorgula/"
    + uye.getTcKimlikNo() + "/" + siparisTutari).asString();

if(response.getBody().equals("Ödeme İşlemi Başarılı Bir Şekilde Tamamlandı!")) {
    siparisService.siparisKaydet(yeniSiparis);
}
```

Bu yapı sayesinde **API1, API2'den bağımsız bir şekilde** sipariş işlemlerini tamamlar, ancak ödeme işlemi **API2'de** gerçekleştirilir. 

---
