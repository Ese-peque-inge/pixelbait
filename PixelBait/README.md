# Pixel Bait — Proyecto Android (código generado)

App de escaneo y análisis de seguridad de códigos QR integrada con VirusTotal, generada según el documento de requerimientos y el diseño de Figma compartidos.

## Cómo abrir y ejecutar

1. Abre Android Studio (versión reciente, Koala/Ladybug o superior recomendado).
2. `File → Open...` y selecciona la carpeta raíz `PixelBait/` (donde está `settings.gradle.kts`).
3. Espera a que Gradle sincronice — la primera vez descargará dependencias (Compose, Hilt, CameraX, ML Kit, Retrofit, Room, etc.), así que necesitas conexión a internet.
4. Conecta tu celular Android por USB con **Depuración USB activada** (Ajustes → Opciones de desarrollador), o usa un emulador con Google Play Services (necesario para ML Kit).
5. Dale a **Run ▶** con tu dispositivo/emulador seleccionado.

## Antes de compilar: cosas que revisar

Este proyecto fue generado completo (arquitectura MVVM, Compose, Hilt, CameraX + ML Kit, Retrofit, Room, WorkManager, EncryptedSharedPreferences), pero en un proyecto de este tamaño **es normal que la primera sincronización de Gradle muestre 1-2 ajustes de versión**. Si eso pasa:

- Deja que Android Studio te sugiera el *Quick Fix* (normalmente ajustar una versión de AGP/Kotlin/KSP).
- Si ves un error de KSP/Kapt, verifica que la versión del plugin `com.google.devtools.ksp` en `build.gradle.kts` (raíz) sea compatible con tu versión de Kotlin instalada en Android Studio.
- Si tu Android Studio trae una versión de Gradle distinta a la definida en `gradle/wrapper/gradle-wrapper.properties`, dale a "Trust and sync" y deja que use el wrapper del proyecto.

## Cómo probar el análisis de QR de verdad

La app te pide tu propia API Key de VirusTotal en el onboarding (paso 3), tal como especifica el documento de requerimientos (nunca se usa una clave propia de la empresa). Para probarla:

1. Crea una cuenta gratuita en https://www.virustotal.com/
2. Ve a tu perfil → API Key, cópiala.
3. Pégala en la pantalla "Configuración de API" al abrir la app por primera vez.
4. Genera un QR de prueba con cualquier URL (por ejemplo con `https://www.qr-code-generator.com/`) y escanéalo.

La API gratuita de VirusTotal tiene límite de ~4 consultas/minuto y ~500/día — la app ya maneja el caso de cuota agotada (mensaje "Límite alcanzado, intenta más tarde").

## Qué está implementado

- Onboarding completo (Términos → Cámara → API Key) con stepper, tal como en el mockup de Figma.
- Escáner QR en vivo con CameraX + ML Kit (100% procesamiento local).
- Overlay de resultado con semáforo de 3 niveles (color + ícono + texto), motores de detección, barra de criticidad y contadores.
- Manejo de timeouts (aviso a los 5s, cancelación a los 15s), sin conexión, y cuota agotada.
- Historial local en Room con expiración automática a 7 días (WorkManager) y aviso previo.
- Notificaciones locales (reinicio de cuota, aviso de expiración de historial).
- Pantalla de Ajustes (paleta de marca, tema oscuro, T&C, actualizar API Key, borrar historial, sitio oficial).
- API Key cifrada con Android Keystore / EncryptedSharedPreferences.
- Pantalla de "sesión monitoreada" (`MonitorStubScreen`) implementada **solo como UI**, ya que el monitoreo real de WebView está fuera de alcance de esta fase según el documento de requerimientos (sección 3.2). Está lista para conectar su lógica en una fase futura.

## Estructura del proyecto

```
app/src/main/java/com/pixelbait/app/
├── core/           # theme, security (Keystore), network (Retrofit/VirusTotal), notifications
├── data/           # local (Room), repository, worker (WorkManager)
├── domain/         # modelos de dominio (RiskLevel, ScanResult)
├── di/             # módulos Hilt
└── ui/
    ├── onboarding/ # Términos, Cámara, API Key
    ├── scanner/    # Escáner QR (CameraX + ML Kit)
    ├── result/     # Overlay de resultado del análisis
    ├── history/    # Historial de escaneos
    ├── settings/   # Ajustes
    ├── monitor/    # Pantalla UI-only (fuera de alcance funcional)
    └── navigation/ # NavGraph y rutas
```
