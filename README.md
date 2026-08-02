# Pixel Bait — Proyecto Android 


## Cómo abrir y ejecutar

1. Abre Android Studio (versión reciente, Koala/Ladybug o superior recomendado).
2. `File → Open...` y selecciona la carpeta raíz `PixelBait/` (donde está `settings.gradle.kts`).
3. Espera a que Gradle sincronice — la primera vez descargará dependencias (Compose, Hilt, CameraX, ML Kit, Retrofit, Room, etc.), así que necesitas conexión a internet.
4. Conecta tu celular Android por USB con **Depuración USB activada** (Ajustes → Opciones de desarrollador), o usa un emulador con Google Play Services (necesario para ML Kit).
5. Dale a **Run ▶** con tu dispositivo/emulador seleccionado.

Este proyecto fue generado completo (arquitectura MVVM, Compose, Hilt, CameraX + ML Kit, Retrofit, Room, WorkManager, EncryptedSharedPreferences).

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





# Pixel Bait — Pagina Frond

**Pixel Bait** La app de ciberseguridad para Android que analiza códigos QR y URLs en busca de phishing, malware y sitios inseguros.

Construida con [Next.js](https://nextjs.org), React 19 y Tailwind CSS 4.

## Requisitos previos

Antes de empezar, instala lo siguiente en tu computadora:

- **[Node.js](https://nodejs.org)** versión 20 o superior (incluye `npm`)
- **[pnpm](https://pnpm.io)** (gestor de paquetes usado en este proyecto)

Para instalar pnpm, abre una terminal y ejecuta:

```bash
npm install -g pnpm
```

Verifica que todo esté instalado correctamente:

```bash
node -v
pnpm -v
```

> **Nota para usuarios de Windows:** si `npm` o `pnpm` no funcionan en PowerShell por un error de "ejecución de scripts deshabilitada", usa el **Símbolo del sistema (CMD)** en su lugar, o habilita los scripts con:
> ```powershell
> Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
> ```

## Instalación

1. Clona este repositorio:

   ```bash
   git clone https://github.com/tu-usuario/pixel-bait-landing-page.git
   cd pixel-bait-landing-page
   ```

2. Instala las dependencias:

   ```bash
   pnpm install
   ```

   Si pnpm muestra un mensaje como `Ignored build scripts`, ejecuta:

   ```bash
   pnpm approve-builds
   ```

   Selecciona todos los paquetes listados (por ejemplo `sharp`, `msw`) y confirma. Luego vuelve a correr `pnpm install`.

## Ejecutar en modo desarrollo

```bash
pnpm dev
```

Abre tu navegador en [http://localhost:3000](http://localhost:3000) para ver el sitio. La página se recarga automáticamente al guardar cambios en el código.

## Compilar para producción

```bash
pnpm build
pnpm start
```

Esto genera una versión optimizada del sitio y la sirve en `http://localhost:3000`.

## Estructura del proyecto

```
├── app/                    # Rutas y páginas (App Router de Next.js)
│   ├── page.tsx            # Página principal
│   ├── layout.tsx          # Layout raíz (fuentes, metadata, analytics)
│   └── privacidad/         # Página de política de privacidad
├── components/             # Componentes reutilizables de la UI
│   └── ui/                 # Componentes base (shadcn/ui)
├── lib/                    # Funciones utilitarias
├── public/                 # Imágenes y assets estáticos
├── next.config.mjs         # Configuración de Next.js
└── package.json            # Dependencias y scripts
```

## Scripts disponibles

| Comando        | Descripción                                    |
|-----------------|------------------------------------------------|
| `pnpm dev`      | Levanta el servidor de desarrollo              |
| `pnpm build`    | Compila el proyecto para producción            |
| `pnpm start`    | Sirve la build de producción                   |
| `pnpm lint`     | Revisa el código con ESLint                    |
