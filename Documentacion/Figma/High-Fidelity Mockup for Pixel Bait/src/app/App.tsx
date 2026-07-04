import { useState } from "react";
import {
  Shield,
  QrCode,
  History,
  Settings,
  ChevronRight,
  AlertTriangle,
  CheckCircle,
  XCircle,
  Camera,
  MapPin,
  Download,
  Lock,
  Globe,
  ArrowLeft,
  RotateCcw,
  Share2,
  MoreVertical,
  Wifi,
  Battery,
  Signal,
  Clock,
  Trash2,
  Info,
  Moon,
  Sun,
  Key,
  ExternalLink,
  Bell,
  Eye,
  EyeOff,
  ShieldAlert,
  ShieldCheck,
  Zap,
  RefreshCw,
} from "lucide-react";

// ── Shared phone chrome ──────────────────────────────────────────────────────
function PhoneFrame({
  children,
  label,
  className = "",
}: {
  children: React.ReactNode;
  label: string;
  className?: string;
}) {
  return (
    <div className={`flex flex-col items-center gap-3 ${className}`}>
      <span
        className="text-xs font-mono font-semibold tracking-widest uppercase px-3 py-1 rounded-full"
        style={{ color: "#a78bfa", background: "rgba(107,47,160,0.15)", border: "1px solid rgba(107,47,160,0.3)" }}
      >
        {label}
      </span>
      <div
        className="relative flex flex-col overflow-hidden"
        style={{
          width: 320,
          height: 650,
          borderRadius: 40,
          background: "#000",
          boxShadow:
            "0 0 0 2px #1B2A4A, 0 0 0 4px rgba(107,47,160,0.5), 0 32px 80px rgba(0,0,0,0.8), 0 0 60px rgba(107,47,160,0.15)",
          fontFamily: "'Inter', sans-serif",
        }}
      >
        {/* Notch */}
        <div
          className="absolute top-0 left-1/2 -translate-x-1/2 z-50 flex items-center justify-center"
          style={{ width: 110, height: 28, background: "#000", borderRadius: "0 0 18px 18px" }}
        >
          <div style={{ width: 8, height: 8, borderRadius: "50%", background: "#1a1a1a", border: "1px solid #333" }} />
        </div>
        {/* Status bar */}
        <div
          className="flex items-center justify-between px-6 pt-2 pb-1 flex-shrink-0"
          style={{ paddingTop: 10, background: "transparent" }}
        >
          <span style={{ fontSize: 10, color: "#9ca3af", fontWeight: 600, letterSpacing: 0.3 }}>9:41</span>
          <div className="flex items-center gap-1">
            <Signal size={10} color="#9ca3af" />
            <Wifi size={10} color="#9ca3af" />
            <Battery size={10} color="#9ca3af" />
          </div>
        </div>
        {/* Screen content */}
        <div className="flex-1 overflow-hidden">{children}</div>
        {/* Home indicator */}
        <div className="flex items-center justify-center pb-2 flex-shrink-0">
          <div style={{ width: 100, height: 4, borderRadius: 2, background: "#2d2d2d" }} />
        </div>
      </div>
    </div>
  );
}

// ── Neon / glowing helpers ───────────────────────────────────────────────────
const VIOLET = "#6B2FA0";
const BLUE = "#1B2A4A";
const NEON_CYAN = "#06b6d4";
const NEON_GREEN = "#10b981";
const NEON_RED = "#ef4444";
const NEON_ORANGE = "#f97316";

function NeonBadge({
  children,
  color = VIOLET,
  size = "sm",
}: {
  children: React.ReactNode;
  color?: string;
  size?: "xs" | "sm";
}) {
  const pad = size === "xs" ? "2px 6px" : "3px 10px";
  const fs = size === "xs" ? 9 : 10;
  return (
    <span
      style={{
        display: "inline-flex",
        alignItems: "center",
        gap: 3,
        padding: pad,
        borderRadius: 99,
        background: `${color}22`,
        border: `1px solid ${color}66`,
        color: color,
        fontSize: fs,
        fontWeight: 700,
        letterSpacing: 0.4,
        textTransform: "uppercase",
        boxShadow: `0 0 8px ${color}44`,
      }}
    >
      {children}
    </span>
  );
}

// ── SCREEN 1: Onboarding ─────────────────────────────────────────────────────
function Screen1Onboarding() {
  const [step, setStep] = useState(0); // 0=T&C, 1=Camera, 2=API
  const [apiKey, setApiKey] = useState("");
  const [showKey, setShowKey] = useState(false);

  const steps = ["Términos", "Cámara", "API Key"];

  return (
    <div className="flex flex-col h-full" style={{ background: "#000", color: "#f0f2f8" }}>
      {/* Header */}
      <div className="flex flex-col items-center pt-4 pb-3 px-5">
        <div className="flex items-center gap-2 mb-3">
          <div
            style={{
              width: 32,
              height: 32,
              borderRadius: 10,
              background: `linear-gradient(135deg, ${VIOLET}, #4f1d87)`,
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              boxShadow: `0 0 16px ${VIOLET}66`,
            }}
          >
            <Shield size={16} color="#fff" />
          </div>
          <span style={{ fontSize: 15, fontWeight: 800, letterSpacing: 1, color: "#f0f2f8" }}>PIXEL BAIT</span>
        </div>
        {/* Step indicator */}
        <div className="flex items-center gap-2">
          {steps.map((s, i) => (
            <div key={i} className="flex items-center gap-2">
              <div
                onClick={() => setStep(i)}
                style={{
                  display: "flex",
                  alignItems: "center",
                  gap: 4,
                  cursor: "pointer",
                  padding: "3px 8px",
                  borderRadius: 99,
                  background: i === step ? `${VIOLET}33` : "transparent",
                  border: `1px solid ${i === step ? VIOLET : "#2a2a3a"}`,
                  transition: "all 0.2s",
                }}
              >
                <div
                  style={{
                    width: 16,
                    height: 16,
                    borderRadius: "50%",
                    background: i < step ? NEON_GREEN : i === step ? VIOLET : "#1a1a2e",
                    border: `1.5px solid ${i <= step ? (i < step ? NEON_GREEN : VIOLET) : "#3a3a5a"}`,
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    fontSize: 8,
                    color: "#fff",
                    fontWeight: 700,
                  }}
                >
                  {i < step ? "✓" : i + 1}
                </div>
                <span style={{ fontSize: 9, color: i === step ? "#c4a8e8" : "#555", fontWeight: 600 }}>{s}</span>
              </div>
              {i < 2 && <div style={{ width: 12, height: 1, background: i < step ? VIOLET : "#222" }} />}
            </div>
          ))}
        </div>
      </div>

      {/* Content area */}
      <div className="flex-1 flex flex-col px-5 overflow-hidden">
        {step === 0 && (
          <>
            <p style={{ fontSize: 18, fontWeight: 700, marginBottom: 4, color: "#f0f2f8" }}>Términos y Condiciones</p>
            <p style={{ fontSize: 10, color: "#6b7899", marginBottom: 10 }}>Versión 1.0 — Julio 2025</p>
            <div
              className="flex-1 overflow-auto pr-1"
              style={{
                background: "#0d1117",
                borderRadius: 14,
                padding: "12px 14px",
                border: `1px solid rgba(107,47,160,0.2)`,
                marginBottom: 12,
                scrollbarWidth: "thin",
                scrollbarColor: `${VIOLET}44 transparent`,
              }}
            >
              {[
                ["1. Uso de la Aplicación", "Pixel Bait es una herramienta de ciberseguridad diseñada para analizar códigos QR y URLs en busca de amenazas maliciosas. El uso de esta aplicación implica la aceptación plena de estos términos."],
                ["2. Recopilación de Datos", "La aplicación analiza URLs escaneadas mediante la API de VirusTotal. No almacenamos datos personales ni compartimos información con terceros no autorizados. El historial de escaneos se guarda localmente por 7 días."],
                ["3. Permisos Requeridos", "La app requiere acceso a la cámara para escanear códigos QR. Este permiso es estrictamente necesario para la funcionalidad principal y no se utilizará para ningún otro propósito."],
                ["4. Limitación de Responsabilidad", "Pixel Bait proporciona análisis basados en bases de datos de terceros (VirusTotal). No garantizamos detección al 100% de todas las amenazas. El usuario es responsable de sus acciones al navegar por URLs externas."],
                ["5. Privacidad y Seguridad", "Todos los datos de sesión son cifrados. La clave API de VirusTotal se almacena de forma segura en el dispositivo mediante encriptación de clave simétrica."],
              ].map(([title, body]) => (
                <div key={title} style={{ marginBottom: 12 }}>
                  <p style={{ fontSize: 10, fontWeight: 700, color: "#a78bfa", marginBottom: 3 }}>{title}</p>
                  <p style={{ fontSize: 9.5, color: "#8892a8", lineHeight: 1.6 }}>{body}</p>
                </div>
              ))}
            </div>
          </>
        )}

        {step === 1 && (
          <div className="flex-1 flex flex-col items-center justify-center">
            <div
              style={{
                width: "100%",
                background: "#0d1117",
                borderRadius: 20,
                padding: "28px 20px",
                border: `1px solid rgba(107,47,160,0.3)`,
                textAlign: "center",
                marginBottom: 16,
              }}
            >
              <div
                style={{
                  width: 72,
                  height: 72,
                  borderRadius: 24,
                  background: `linear-gradient(135deg, ${VIOLET}44, ${BLUE})`,
                  border: `2px solid ${VIOLET}66`,
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                  margin: "0 auto 16px",
                  boxShadow: `0 0 30px ${VIOLET}33`,
                }}
              >
                <Camera size={32} color={VIOLET} />
              </div>
              <p style={{ fontSize: 17, fontWeight: 700, color: "#f0f2f8", marginBottom: 8 }}>Acceso a Cámara</p>
              <p style={{ fontSize: 11, color: "#8892a8", lineHeight: 1.6, marginBottom: 16 }}>
                Pixel Bait necesita acceso a tu cámara para escanear códigos QR y detectar URLs potencialmente peligrosas en tiempo real.
              </p>
              <div style={{ display: "flex", flexDirection: "column", gap: 8 }}>
                {[
                  [CheckCircle, NEON_GREEN, "Solo activo durante el escaneo"],
                  [CheckCircle, NEON_GREEN, "Nunca graba ni almacena imágenes"],
                  [CheckCircle, NEON_GREEN, "Procesamiento 100% local"],
                ].map(([Icon, color, text], i) => (
                  <div key={i} style={{ display: "flex", alignItems: "center", gap: 8, padding: "8px 12px", background: "#111827", borderRadius: 10 }}>
                    <Icon size={14} color={color as string} />
                    <span style={{ fontSize: 10, color: "#b0bcc8" }}>{text as string}</span>
                  </div>
                ))}
              </div>
            </div>
            <NeonBadge color={NEON_CYAN}>
              <Shield size={9} /> Permiso requerido por Android
            </NeonBadge>
          </div>
        )}

        {step === 2 && (
          <div className="flex-1 flex flex-col">
            <p style={{ fontSize: 17, fontWeight: 700, marginBottom: 4, color: "#f0f2f8" }}>Configuración de API</p>
            <p style={{ fontSize: 10, color: "#6b7899", marginBottom: 14 }}>Ingresa tu clave para activar el análisis de amenazas.</p>

            <div
              style={{
                background: "#0d1117",
                borderRadius: 16,
                padding: "16px",
                border: `1px solid rgba(107,47,160,0.25)`,
                marginBottom: 12,
              }}
            >
              <div style={{ display: "flex", alignItems: "center", gap: 6, marginBottom: 12 }}>
                <div
                  style={{
                    width: 28,
                    height: 28,
                    borderRadius: 8,
                    background: "#1a0d2e",
                    border: `1px solid ${VIOLET}55`,
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                  }}
                >
                  <Key size={12} color={VIOLET} />
                </div>
                <span style={{ fontSize: 11, fontWeight: 600, color: "#c4a8e8" }}>VirusTotal API Key</span>
              </div>

              <div
                style={{
                  display: "flex",
                  alignItems: "center",
                  gap: 8,
                  background: "#080d14",
                  borderRadius: 10,
                  padding: "10px 12px",
                  border: `1px solid ${apiKey ? VIOLET + "88" : "#2a2a3a"}`,
                  marginBottom: 8,
                  transition: "border-color 0.2s",
                  boxShadow: apiKey ? `0 0 12px ${VIOLET}22` : "none",
                }}
              >
                <input
                  type={showKey ? "text" : "password"}
                  value={apiKey}
                  onChange={(e) => setApiKey(e.target.value)}
                  placeholder="e.g. a1b2c3d4e5f6..."
                  style={{
                    flex: 1,
                    background: "transparent",
                    border: "none",
                    outline: "none",
                    color: "#f0f2f8",
                    fontSize: 11,
                    fontFamily: "'JetBrains Mono', monospace",
                  }}
                />
                <button onClick={() => setShowKey(!showKey)} style={{ background: "none", border: "none", cursor: "pointer", padding: 0 }}>
                  {showKey ? <EyeOff size={13} color="#6b7899" /> : <Eye size={13} color="#6b7899" />}
                </button>
              </div>

              <a
                href="#"
                style={{
                  display: "flex",
                  alignItems: "center",
                  gap: 4,
                  fontSize: 10,
                  color: "#a78bfa",
                  textDecoration: "none",
                }}
              >
                <ExternalLink size={9} />
                ¿No tienes una clave? Ayuda
              </a>
            </div>

            {/* Info box */}
            <div
              style={{
                background: "#0a1628",
                borderRadius: 12,
                padding: "12px",
                border: `1px solid ${BLUE}`,
                display: "flex",
                gap: 10,
                marginBottom: 12,
              }}
            >
              <Info size={14} color={NEON_CYAN} style={{ flexShrink: 0, marginTop: 1 }} />
              <p style={{ fontSize: 9.5, color: "#7a8caa", lineHeight: 1.6 }}>
                Tu clave API de VirusTotal se almacena de forma segura en el dispositivo. Nunca se transmite a servidores externos de Pixel Bait.
              </p>
            </div>

            {!apiKey && (
              <div style={{ display: "flex", alignItems: "center", gap: 6, marginBottom: 8 }}>
                <div style={{ width: 6, height: 6, borderRadius: "50%", background: "#ef4444" }} />
                <span style={{ fontSize: 9, color: "#6b7899" }}>Ingresa una clave para continuar</span>
              </div>
            )}
            {apiKey && (
              <div style={{ display: "flex", alignItems: "center", gap: 6, marginBottom: 8 }}>
                <div style={{ width: 6, height: 6, borderRadius: "50%", background: NEON_GREEN, boxShadow: `0 0 6px ${NEON_GREEN}` }} />
                <span style={{ fontSize: 9, color: NEON_GREEN }}>Clave detectada — lista para guardar</span>
              </div>
            )}
          </div>
        )}
      </div>

      {/* Bottom button */}
      <div className="px-5 pb-3">
        <button
          onClick={() => step < 2 && setStep(step + 1)}
          disabled={step === 2 && !apiKey}
          style={{
            width: "100%",
            padding: "14px",
            borderRadius: 14,
            border: "none",
            cursor: step === 2 && !apiKey ? "not-allowed" : "pointer",
            background:
              step === 2 && !apiKey
                ? "#1a1a2e"
                : `linear-gradient(135deg, ${VIOLET}, #4f1d87)`,
            color: step === 2 && !apiKey ? "#3a3a5a" : "#fff",
            fontSize: 13,
            fontWeight: 700,
            letterSpacing: 0.5,
            transition: "all 0.2s",
            boxShadow: step === 2 && !apiKey ? "none" : `0 4px 20px ${VIOLET}55`,
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            gap: 8,
          }}
        >
          {step === 2 ? (
            <>
              <Shield size={14} />
              Activar Protección
            </>
          ) : (
            <>
              Siguiente
              <ChevronRight size={15} />
            </>
          )}
        </button>
      </div>
    </div>
  );
}

// ── SCREEN 2: QR Scanner + Result Card ──────────────────────────────────────
function Screen2Scanner() {
  const [showResult, setShowResult] = useState(true);

  return (
    <div className="flex flex-col h-full relative" style={{ background: "#000", color: "#f0f2f8" }}>
      {/* Header */}
      <div
        className="flex items-center justify-between px-4 py-3 flex-shrink-0"
        style={{ background: "rgba(0,0,0,0.9)", borderBottom: `1px solid rgba(107,47,160,0.2)` }}
      >
        <div className="flex items-center gap-2">
          <Shield size={14} color={VIOLET} />
          <span style={{ fontSize: 13, fontWeight: 700, color: "#f0f2f8" }}>Escáner QR</span>
        </div>
        <NeonBadge color={NEON_GREEN}>
          <div style={{ width: 5, height: 5, borderRadius: "50%", background: NEON_GREEN }} />
          Activo
        </NeonBadge>
      </div>

      {/* Camera viewfinder */}
      <div
        className="relative flex-1"
        style={{
          background: `linear-gradient(180deg, #0a0a12 0%, #050508 100%)`,
          overflow: "hidden",
        }}
      >
        {/* Fake camera noise */}
        <div
          style={{
            position: "absolute",
            inset: 0,
            backgroundImage: `radial-gradient(circle at 50% 40%, #1B2A4A33 0%, transparent 60%)`,
          }}
        />

        {/* Grid lines */}
        <svg style={{ position: "absolute", inset: 0, width: "100%", height: "100%", opacity: 0.07 }}>
          {[...Array(8)].map((_, i) => (
            <line key={`h${i}`} x1="0" y1={`${(i + 1) * 12}%`} x2="100%" y2={`${(i + 1) * 12}%`} stroke="#6B2FA0" strokeWidth="1" />
          ))}
          {[...Array(6)].map((_, i) => (
            <line key={`v${i}`} x1={`${(i + 1) * 16}%`} y1="0" x2={`${(i + 1) * 16}%`} y2="100%" stroke="#6B2FA0" strokeWidth="1" />
          ))}
        </svg>

        {/* QR finder overlay */}
        <div
          style={{
            position: "absolute",
            top: showResult ? "8%" : "25%",
            left: "50%",
            transform: "translateX(-50%)",
            width: 160,
            height: 160,
            transition: "top 0.4s ease",
          }}
        >
          {/* Corner brackets */}
          {[
            { top: 0, left: 0, borderTop: `3px solid ${VIOLET}`, borderLeft: `3px solid ${VIOLET}` },
            { top: 0, right: 0, borderTop: `3px solid ${VIOLET}`, borderRight: `3px solid ${VIOLET}` },
            { bottom: 0, left: 0, borderBottom: `3px solid ${VIOLET}`, borderLeft: `3px solid ${VIOLET}` },
            { bottom: 0, right: 0, borderBottom: `3px solid ${VIOLET}`, borderRight: `3px solid ${VIOLET}` },
          ].map((style, i) => (
            <div
              key={i}
              style={{
                position: "absolute",
                width: 24,
                height: 24,
                ...style,
                boxShadow: `0 0 8px ${VIOLET}88`,
              }}
            />
          ))}

          {/* QR code mock */}
          <div
            style={{
              position: "absolute",
              inset: 12,
              background: "#fff",
              borderRadius: 4,
              display: "grid",
              gridTemplateColumns: "repeat(9,1fr)",
              gridTemplateRows: "repeat(9,1fr)",
              gap: 1.5,
              padding: 8,
              opacity: 0.9,
            }}
          >
            {[...Array(81)].map((_, i) => {
              const r = Math.floor(i / 9), c = i % 9;
              const corner = (r < 3 && c < 3) || (r < 3 && c > 5) || (r > 5 && c < 3);
              const fill = corner ? "#1B2A4A" : Math.random() > 0.55 ? "#1B2A4A" : "transparent";
              return (
                <div key={i} style={{ background: fill, borderRadius: 1 }} />
              );
            })}
          </div>

          {/* Scan line */}
          <div
            style={{
              position: "absolute",
              left: 12,
              right: 12,
              height: 2,
              background: `linear-gradient(90deg, transparent, ${NEON_CYAN}, transparent)`,
              boxShadow: `0 0 8px ${NEON_CYAN}`,
              top: "40%",
              animation: "scanLine 1.8s ease-in-out infinite",
            }}
          />
        </div>

        {!showResult && (
          <p
            style={{
              position: "absolute",
              bottom: "30%",
              left: "50%",
              transform: "translateX(-50%)",
              fontSize: 11,
              color: "#8892a8",
              whiteSpace: "nowrap",
            }}
          >
            Apunta al código QR
          </p>
        )}

        {/* Result Card */}
        {showResult && (
          <div
            style={{
              position: "absolute",
              bottom: 0,
              left: 0,
              right: 0,
              background: "#0a0510",
              borderRadius: "20px 20px 0 0",
              padding: "16px 16px 8px",
              border: `1px solid ${NEON_RED}44`,
              boxShadow: `0 -8px 40px ${NEON_RED}22, 0 -2px 0 ${NEON_RED}66`,
            }}
          >
            {/* Drag handle */}
            <div style={{ width: 36, height: 4, background: "#2a1a3a", borderRadius: 2, margin: "0 auto 12px" }} />

            {/* Threat header */}
            <div style={{ display: "flex", alignItems: "flex-start", justifyContent: "space-between", marginBottom: 12 }}>
              <div style={{ display: "flex", alignItems: "center", gap: 10 }}>
                <div
                  style={{
                    width: 44,
                    height: 44,
                    borderRadius: 14,
                    background: "#1a0505",
                    border: `2px solid ${NEON_RED}88`,
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    boxShadow: `0 0 16px ${NEON_RED}44`,
                  }}
                >
                  <ShieldAlert size={22} color={NEON_RED} />
                </div>
                <div>
                  <div style={{ display: "flex", alignItems: "center", gap: 6, marginBottom: 3 }}>
                    <span style={{ fontSize: 14, fontWeight: 800, color: NEON_RED }}>ALTO RIESGO</span>
                    <NeonBadge color={NEON_RED} size="xs">MALICIOSO</NeonBadge>
                  </div>
                  <p style={{ fontSize: 9, color: "#8892a8" }}>Detectado por VirusTotal</p>
                </div>
              </div>
              <button
                onClick={() => setShowResult(false)}
                style={{ background: "none", border: "none", cursor: "pointer", padding: 4 }}
              >
                <XCircle size={16} color="#3a3a5a" />
              </button>
            </div>

            {/* URL */}
            <div
              style={{
                background: "#0d0d1a",
                borderRadius: 10,
                padding: "8px 10px",
                marginBottom: 10,
                border: `1px solid #2a1a3a`,
                display: "flex",
                alignItems: "center",
                gap: 6,
              }}
            >
              <Globe size={10} color={NEON_RED} style={{ flexShrink: 0 }} />
              <span style={{ fontSize: 9.5, color: "#c0c0d0", fontFamily: "'JetBrains Mono', monospace", wordBreak: "break-all" }}>
                http://login-secure-bank.xyz/verify?token=aBc123
              </span>
            </div>

            {/* Criticality bar */}
            <div style={{ marginBottom: 10 }}>
              <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: 5 }}>
                <span style={{ fontSize: 9, color: "#8892a8", fontWeight: 600 }}>Nivel de Criticidad</span>
                <span style={{ fontSize: 9, color: NEON_RED, fontWeight: 700 }}>92 / 100</span>
              </div>
              <div style={{ height: 6, background: "#1a0a0a", borderRadius: 3, overflow: "hidden" }}>
                <div
                  style={{
                    height: "100%",
                    width: "92%",
                    background: `linear-gradient(90deg, ${NEON_ORANGE}, ${NEON_RED})`,
                    borderRadius: 3,
                    boxShadow: `0 0 8px ${NEON_RED}66`,
                  }}
                />
              </div>
            </div>

            {/* Engine results */}
            <div style={{ marginBottom: 10 }}>
              <p style={{ fontSize: 9, color: "#6b7899", marginBottom: 6, fontWeight: 600 }}>MOTORES DE DETECCIÓN</p>
              <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: 5 }}>
                {[
                  ["Malware Domain", NEON_RED, "Phishing"],
                  ["Kaspersky", NEON_RED, "Malicious"],
                  ["Google SafeBrowsing", NEON_RED, "Deceptive"],
                  ["Bitdefender", NEON_ORANGE, "Suspicious"],
                  ["McAfee", NEON_RED, "Phishing"],
                  ["Avast", "transparent", "Clean"],
                ].map(([name, color, label], i) => (
                  <div
                    key={i}
                    style={{
                      display: "flex",
                      alignItems: "center",
                      gap: 5,
                      padding: "5px 8px",
                      background: color !== "transparent" ? `${color}11` : "#0d0d1a",
                      borderRadius: 8,
                      border: `1px solid ${color !== "transparent" ? color + "33" : "#1a1a2e"}`,
                    }}
                  >
                    {color !== "transparent" ? (
                      <XCircle size={10} color={color as string} />
                    ) : (
                      <CheckCircle size={10} color="#374151" />
                    )}
                    <div>
                      <p style={{ fontSize: 8, color: color !== "transparent" ? "#c0c0d0" : "#555", fontWeight: 600 }}>{name as string}</p>
                      <p style={{ fontSize: 7, color: color !== "transparent" ? color as string : "#3a3a5a" }}>{label as string}</p>
                    </div>
                  </div>
                ))}
              </div>
            </div>

            {/* Stats row */}
            <div
              style={{
                display: "flex",
                gap: 6,
                padding: "8px 10px",
                background: "#0a0010",
                borderRadius: 10,
                border: `1px solid ${NEON_RED}22`,
                marginBottom: 12,
              }}
            >
              {[
                ["5", "Maliciosos", NEON_RED],
                ["1", "Sospechosos", NEON_ORANGE],
                ["56", "Sin detección", "#374151"],
              ].map(([val, label, color]) => (
                <div key={label} style={{ flex: 1, textAlign: "center" }}>
                  <p style={{ fontSize: 16, fontWeight: 800, color: color as string, lineHeight: 1 }}>{val}</p>
                  <p style={{ fontSize: 7.5, color: "#555", marginTop: 1 }}>{label as string}</p>
                </div>
              ))}
            </div>

            {/* Actions */}
            <div style={{ display: "flex", gap: 8 }}>
              <button
                style={{
                  flex: 1,
                  padding: "10px",
                  borderRadius: 12,
                  border: `1px solid ${NEON_RED}55`,
                  background: "#1a0505",
                  color: NEON_RED,
                  fontSize: 11,
                  fontWeight: 700,
                  cursor: "pointer",
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                  gap: 5,
                }}
              >
                <XCircle size={12} />
                Bloquear
              </button>
              <button
                style={{
                  flex: 1,
                  padding: "10px",
                  borderRadius: 12,
                  border: `1px solid #2a2a3a`,
                  background: "#111",
                  color: "#8892a8",
                  fontSize: 11,
                  fontWeight: 600,
                  cursor: "pointer",
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                  gap: 5,
                }}
              >
                <ExternalLink size={12} />
                Ver reporte
              </button>
            </div>
          </div>
        )}
      </div>

      <style>{`
        @keyframes scanLine {
          0%, 100% { top: 15%; opacity: 0.6; }
          50% { top: 75%; opacity: 1; }
        }
      `}</style>
    </div>
  );
}

// ── SCREEN 3: In-App Browser ─────────────────────────────────────────────────
function Screen3Browser() {
  const [showAlert, setShowAlert] = useState(true);
  const url = "http://login-secure-bank.xyz/verify";

  return (
    <div className="flex flex-col h-full" style={{ background: "#000", color: "#f0f2f8" }}>
      {/* Browser top bar */}
      <div
        style={{
          background: "#0a0510",
          borderBottom: `1px solid rgba(107,47,160,0.25)`,
          padding: "8px 10px",
          flexShrink: 0,
        }}
      >
        {/* Nav row */}
        <div style={{ display: "flex", alignItems: "center", gap: 6, marginBottom: 8 }}>
          <button style={{ background: "none", border: "none", cursor: "pointer", padding: 4 }}>
            <ArrowLeft size={14} color="#6b7899" />
          </button>
          <button style={{ background: "none", border: "none", cursor: "pointer", padding: 4 }}>
            <RotateCcw size={12} color="#6b7899" />
          </button>

          {/* Address bar */}
          <div
            style={{
              flex: 1,
              display: "flex",
              alignItems: "center",
              gap: 6,
              background: "#0d0d1a",
              borderRadius: 10,
              padding: "6px 10px",
              border: `1px solid ${NEON_RED}55`,
              boxShadow: `0 0 10px ${NEON_RED}11`,
            }}
          >
            <AlertTriangle size={10} color={NEON_RED} style={{ flexShrink: 0 }} />
            <span
              style={{
                fontSize: 9.5,
                color: "#c0c0d0",
                fontFamily: "'JetBrains Mono', monospace",
                overflow: "hidden",
                textOverflow: "ellipsis",
                whiteSpace: "nowrap",
                flex: 1,
              }}
            >
              {url}
            </span>
          </div>

          <button style={{ background: "none", border: "none", cursor: "pointer", padding: 4 }}>
            <Share2 size={12} color="#6b7899" />
          </button>
          <button style={{ background: "none", border: "none", cursor: "pointer", padding: 4 }}>
            <MoreVertical size={12} color="#6b7899" />
          </button>
        </div>

        {/* Security warning strip */}
        <div
          style={{
            display: "flex",
            alignItems: "center",
            gap: 6,
            padding: "4px 8px",
            background: `${NEON_RED}11`,
            borderRadius: 8,
            border: `1px solid ${NEON_RED}33`,
          }}
        >
          <ShieldAlert size={10} color={NEON_RED} />
          <span style={{ fontSize: 8.5, color: NEON_RED, fontWeight: 600 }}>
            SITIO NO SEGURO — Pixel Bait está monitoreando esta sesión
          </span>
        </div>
      </div>

      {/* Mock web page content */}
      <div
        className="flex-1 relative overflow-hidden"
        style={{ background: "#f9f9f9" }}
      >
        {/* Fake web page */}
        <div style={{ padding: "16px 14px" }}>
          <div style={{ height: 32, background: "#1B2A4A", borderRadius: 6, marginBottom: 12, display: "flex", alignItems: "center", paddingLeft: 12 }}>
            <div style={{ width: 60, height: 12, background: "rgba(255,255,255,0.3)", borderRadius: 3 }} />
          </div>
          <div style={{ height: 100, background: "#e5e7eb", borderRadius: 8, marginBottom: 10 }} />
          <div style={{ height: 12, background: "#d1d5db", borderRadius: 3, marginBottom: 6, width: "80%" }} />
          <div style={{ height: 12, background: "#d1d5db", borderRadius: 3, marginBottom: 6, width: "65%" }} />
          <div style={{ height: 12, background: "#d1d5db", borderRadius: 3, marginBottom: 14, width: "75%" }} />
          {/* Fake login form */}
          <div style={{ background: "#fff", borderRadius: 10, padding: 12, border: "1px solid #e5e7eb", boxShadow: "0 2px 8px rgba(0,0,0,0.08)" }}>
            <div style={{ height: 10, background: "#6b7280", borderRadius: 2, width: 80, marginBottom: 10 }} />
            <div style={{ height: 32, background: "#f3f4f6", borderRadius: 6, marginBottom: 8, border: "1px solid #d1d5db" }} />
            <div style={{ height: 32, background: "#f3f4f6", borderRadius: 6, marginBottom: 10, border: "1px solid #d1d5db" }} />
            <div style={{ height: 34, background: "#1B2A4A", borderRadius: 6 }} />
          </div>
        </div>

        {/* Dimming overlay when alert shows */}
        {showAlert && (
          <div
            style={{
              position: "absolute",
              inset: 0,
              background: "rgba(0,0,0,0.7)",
              backdropFilter: "blur(2px)",
            }}
          />
        )}

        {/* ALERT POPUP */}
        {showAlert && (
          <div
            style={{
              position: "absolute",
              top: "50%",
              left: "50%",
              transform: "translate(-50%,-50%)",
              width: "88%",
              background: "#08020f",
              borderRadius: 18,
              padding: "18px 16px",
              border: `2px solid ${NEON_RED}77`,
              boxShadow: `0 0 40px ${NEON_RED}33, 0 20px 60px rgba(0,0,0,0.8)`,
            }}
          >
            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "flex-start", marginBottom: 12 }}>
              <div style={{ display: "flex", alignItems: "center", gap: 8 }}>
                <div
                  style={{
                    width: 36,
                    height: 36,
                    borderRadius: 12,
                    background: "#1a0505",
                    border: `2px solid ${NEON_RED}`,
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    boxShadow: `0 0 14px ${NEON_RED}55`,
                  }}
                >
                  <Bell size={18} color={NEON_RED} />
                </div>
                <div>
                  <p style={{ fontSize: 11, fontWeight: 800, color: NEON_RED, marginBottom: 1 }}>¡ACCESO BLOQUEADO!</p>
                  <p style={{ fontSize: 8, color: "#6b7899" }}>Pixel Bait — Protección Activa</p>
                </div>
              </div>
              <button
                onClick={() => setShowAlert(false)}
                style={{ background: "none", border: "none", cursor: "pointer", padding: 2 }}
              >
                <XCircle size={14} color="#3a3a5a" />
              </button>
            </div>

            <div
              style={{
                background: "#120008",
                borderRadius: 10,
                padding: "10px 12px",
                marginBottom: 12,
                border: `1px solid ${NEON_RED}22`,
              }}
            >
              <p style={{ fontSize: 9.5, color: "#c8c0d0", lineHeight: 1.6, marginBottom: 6 }}>
                <strong style={{ color: NEON_RED }}>Anomalía detectada:</strong> El sitio intentó solicitar permisos de geolocalización sin interacción del usuario.
              </p>
              <p style={{ fontSize: 8.5, color: "#6b7899" }}>Comportamiento típico de sitios de phishing — lista negra activada.</p>
            </div>

            <NeonBadge color={NEON_RED}>
              <Lock size={8} />
              Añadido a Lista Negra Local
            </NeonBadge>

            <div style={{ display: "flex", gap: 8, marginTop: 12 }}>
              <button
                onClick={() => setShowAlert(false)}
                style={{
                  flex: 1,
                  padding: "9px",
                  borderRadius: 10,
                  border: `1px solid ${NEON_RED}55`,
                  background: "#1a0505",
                  color: NEON_RED,
                  fontSize: 10,
                  fontWeight: 700,
                  cursor: "pointer",
                }}
              >
                Salir del sitio
              </button>
              <button
                style={{
                  flex: 1,
                  padding: "9px",
                  borderRadius: 10,
                  border: "1px solid #2a2a3a",
                  background: "#111",
                  color: "#555",
                  fontSize: 10,
                  fontWeight: 600,
                  cursor: "pointer",
                }}
              >
                Continuar (riesgo)
              </button>
            </div>
          </div>
        )}
      </div>

      {/* Security Shield Monitor */}
      <div
        style={{
          background: "#060210",
          borderTop: `1px solid rgba(107,47,160,0.3)`,
          padding: "8px 12px",
          flexShrink: 0,
        }}
      >
        <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between", marginBottom: 7 }}>
          <div style={{ display: "flex", alignItems: "center", gap: 5 }}>
            <ShieldCheck size={12} color={VIOLET} />
            <span style={{ fontSize: 9, fontWeight: 700, color: "#a78bfa", letterSpacing: 0.5 }}>MONITOR DE SEGURIDAD</span>
          </div>
          <NeonBadge color={NEON_GREEN} size="xs">
            <Zap size={7} />
            ACTIVO
          </NeonBadge>
        </div>

        <div style={{ display: "flex", gap: 6 }}>
          {[
            [Download, NEON_GREEN, "Descargas", "Bloqueadas"],
            [Camera, NEON_GREEN, "Cámara", "Protegida"],
            [MapPin, NEON_RED, "Ubicación", "Bloqueada"],
          ].map(([Icon, color, label, status]) => (
            <div
              key={label as string}
              style={{
                flex: 1,
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                gap: 4,
                padding: "7px 4px",
                background: `${color as string}11`,
                borderRadius: 10,
                border: `1px solid ${color as string}33`,
              }}
            >
              <Icon size={14} color={color as string} />
              <span style={{ fontSize: 7.5, color: "#c0c0d0", fontWeight: 600 }}>{label as string}</span>
              <span style={{ fontSize: 7, color: color as string, fontWeight: 700 }}>{status as string}</span>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

// ── SCREEN 4: History & Settings ─────────────────────────────────────────────
function Screen4History() {
  const [tab, setTab] = useState<"historial" | "ajustes">("historial");
  const [darkTheme, setDarkTheme] = useState(true);

  const history = [
    { url: "https://paypal-secure.verify-now.com/login", risk: "alto", engines: 5, time: "hace 2h" },
    { url: "https://github.com/pixel-bait/releases", risk: "seguro", engines: 0, time: "hace 5h" },
    { url: "https://amazon-offer.xyz/promo?ref=qr21", risk: "medio", engines: 2, time: "ayer" },
    { url: "https://docs.google.com/spreadsheets/d/1Abc", risk: "seguro", engines: 0, time: "ayer" },
    { url: "https://free-bitcoin-generator.io/claim", risk: "alto", engines: 7, time: "hace 3 días" },
  ];

  const riskColor = (r: string) =>
    r === "alto" ? NEON_RED : r === "medio" ? NEON_ORANGE : NEON_GREEN;
  const riskLabel = (r: string) =>
    r === "alto" ? "Alto riesgo" : r === "medio" ? "Moderado" : "Seguro";

  return (
    <div className="flex flex-col h-full" style={{ background: "#000", color: "#f0f2f8" }}>
      {/* Header */}
      <div
        style={{
          padding: "10px 14px 0",
          background: "#000",
          flexShrink: 0,
        }}
      >
        <div style={{ display: "flex", alignItems: "center", gap: 8, marginBottom: 12 }}>
          <div
            style={{
              width: 28,
              height: 28,
              borderRadius: 9,
              background: `linear-gradient(135deg, ${VIOLET}, #3d1570)`,
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <Shield size={13} color="#fff" />
          </div>
          <span style={{ fontSize: 14, fontWeight: 800, letterSpacing: 0.5 }}>Pixel Bait</span>
        </div>

        {/* Tab switcher */}
        <div
          style={{
            display: "flex",
            background: "#0d0d1a",
            borderRadius: 12,
            padding: 3,
            marginBottom: 14,
          }}
        >
          {(["historial", "ajustes"] as const).map((t) => (
            <button
              key={t}
              onClick={() => setTab(t)}
              style={{
                flex: 1,
                padding: "8px",
                borderRadius: 10,
                border: "none",
                cursor: "pointer",
                background: tab === t ? `linear-gradient(135deg, ${VIOLET}, #4f1d87)` : "transparent",
                color: tab === t ? "#fff" : "#6b7899",
                fontSize: 11,
                fontWeight: 700,
                transition: "all 0.2s",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                gap: 5,
                boxShadow: tab === t ? `0 2px 12px ${VIOLET}44` : "none",
                letterSpacing: 0.3,
              }}
            >
              {t === "historial" ? <History size={12} /> : <Settings size={12} />}
              {t === "historial" ? "Historial" : "Ajustes"}
            </button>
          ))}
        </div>
      </div>

      {/* Content */}
      <div className="flex-1 overflow-auto px-4 pb-4" style={{ scrollbarWidth: "none" }}>
        {tab === "historial" && (
          <>
            <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between", marginBottom: 10 }}>
              <div>
                <p style={{ fontSize: 13, fontWeight: 700, color: "#f0f2f8" }}>Escaneos Recientes</p>
                <p style={{ fontSize: 9, color: "#6b7899" }}>{history.length} registros almacenados</p>
              </div>
              <div style={{ display: "flex", gap: 6, alignItems: "center" }}>
                <NeonBadge color={VIOLET} size="xs">
                  <Clock size={7} />
                  Vigencia: 7 días
                </NeonBadge>
                <button style={{ background: "none", border: "none", cursor: "pointer", padding: 2 }}>
                  <Trash2 size={13} color="#3a3a5a" />
                </button>
              </div>
            </div>

            <div style={{ display: "flex", flexDirection: "column", gap: 6 }}>
              {history.map((item, i) => (
                <div
                  key={i}
                  style={{
                    background: "#0d1117",
                    borderRadius: 14,
                    padding: "11px 12px",
                    border: `1px solid ${riskColor(item.risk)}22`,
                    display: "flex",
                    alignItems: "center",
                    gap: 10,
                  }}
                >
                  <div
                    style={{
                      width: 36,
                      height: 36,
                      borderRadius: 11,
                      background: `${riskColor(item.risk)}15`,
                      border: `1.5px solid ${riskColor(item.risk)}44`,
                      display: "flex",
                      alignItems: "center",
                      justifyContent: "center",
                      flexShrink: 0,
                    }}
                  >
                    {item.risk === "seguro" ? (
                      <ShieldCheck size={16} color={NEON_GREEN} />
                    ) : (
                      <ShieldAlert size={16} color={riskColor(item.risk)} />
                    )}
                  </div>
                  <div style={{ flex: 1, minWidth: 0 }}>
                    <p
                      style={{
                        fontSize: 9,
                        color: "#c0c0d0",
                        fontFamily: "'JetBrains Mono', monospace",
                        overflow: "hidden",
                        textOverflow: "ellipsis",
                        whiteSpace: "nowrap",
                        marginBottom: 4,
                      }}
                    >
                      {item.url}
                    </p>
                    <div style={{ display: "flex", alignItems: "center", gap: 6 }}>
                      <NeonBadge color={riskColor(item.risk)} size="xs">
                        {riskLabel(item.risk)}
                      </NeonBadge>
                      {item.engines > 0 && (
                        <span style={{ fontSize: 8, color: "#555" }}>{item.engines} motores</span>
                      )}
                      <span style={{ fontSize: 8, color: "#3a3a5a", marginLeft: "auto" }}>{item.time}</span>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </>
        )}

        {tab === "ajustes" && (
          <div style={{ display: "flex", flexDirection: "column", gap: 12 }}>
            {/* App info */}
            <div
              style={{
                background: `linear-gradient(135deg, ${BLUE}88, #0d1117)`,
                borderRadius: 16,
                padding: "16px",
                border: `1px solid rgba(107,47,160,0.25)`,
                display: "flex",
                alignItems: "center",
                gap: 12,
              }}
            >
              <div
                style={{
                  width: 48,
                  height: 48,
                  borderRadius: 16,
                  background: `linear-gradient(135deg, ${VIOLET}, #3d1570)`,
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                  boxShadow: `0 0 20px ${VIOLET}44`,
                }}
              >
                <Shield size={24} color="#fff" />
              </div>
              <div>
                <p style={{ fontSize: 14, fontWeight: 800, color: "#f0f2f8" }}>Pixel Bait</p>
                <p style={{ fontSize: 9, color: "#8892a8" }}>Versión 1.0.0 — Protección Activa</p>
                <NeonBadge color={NEON_GREEN} size="xs">
                  <ShieldCheck size={7} /> Licencia válida
                </NeonBadge>
              </div>
            </div>

            {/* Theme switcher */}
            <div
              style={{
                background: "#0d1117",
                borderRadius: 16,
                padding: "14px",
                border: `1px solid rgba(107,47,160,0.2)`,
              }}
            >
              <p style={{ fontSize: 10, fontWeight: 700, color: "#a78bfa", marginBottom: 12, letterSpacing: 0.5 }}>APARIENCIA</p>
              <div style={{ display: "flex", flexDirection: "column", gap: 8 }}>
                {[
                  ["Paleta de Marca", "Azul / Violeta (#1B2A4A + #6B2FA0)", false],
                  ["Tema Oscuro", "Negro absoluto (#000000)", true],
                ].map(([name, desc, isDark]) => (
                  <div
                    key={name as string}
                    onClick={() => setDarkTheme(isDark as boolean)}
                    style={{
                      display: "flex",
                      alignItems: "center",
                      gap: 10,
                      padding: "10px 12px",
                      background: darkTheme === isDark ? `${VIOLET}15` : "#080d14",
                      borderRadius: 12,
                      border: `1px solid ${darkTheme === isDark ? VIOLET + "55" : "#1a1a2e"}`,
                      cursor: "pointer",
                      transition: "all 0.2s",
                    }}
                  >
                    <div
                      style={{
                        width: 28,
                        height: 28,
                        borderRadius: 9,
                        background: isDark ? "#000" : `linear-gradient(135deg, ${BLUE}, ${VIOLET})`,
                        border: `2px solid ${darkTheme === isDark ? VIOLET : "#2a2a3a"}`,
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                      }}
                    >
                      {isDark ? <Moon size={12} color="#a78bfa" /> : <Sun size={12} color="#f0f2f8" />}
                    </div>
                    <div style={{ flex: 1 }}>
                      <p style={{ fontSize: 11, fontWeight: 600, color: "#f0f2f8", marginBottom: 1 }}>{name as string}</p>
                      <p style={{ fontSize: 8.5, color: "#6b7899" }}>{desc as string}</p>
                    </div>
                    <div
                      style={{
                        width: 16,
                        height: 16,
                        borderRadius: "50%",
                        border: `2px solid ${darkTheme === isDark ? VIOLET : "#2a2a3a"}`,
                        background: darkTheme === isDark ? VIOLET : "transparent",
                        boxShadow: darkTheme === isDark ? `0 0 8px ${VIOLET}` : "none",
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                      }}
                    >
                      {darkTheme === isDark && <div style={{ width: 6, height: 6, borderRadius: "50%", background: "#fff" }} />}
                    </div>
                  </div>
                ))}
              </div>
            </div>

            {/* Navigation links */}
            <div
              style={{
                background: "#0d1117",
                borderRadius: 16,
                border: `1px solid rgba(107,47,160,0.2)`,
                overflow: "hidden",
              }}
            >
              <p style={{ fontSize: 10, fontWeight: 700, color: "#a78bfa", padding: "12px 14px 8px", letterSpacing: 0.5 }}>LEGAL & SOPORTE</p>
              {[
                [Info, "Términos y Condiciones", "Revisar el acuerdo de uso"],
                [Key, "Actualizar API Key", "VirusTotal — clave configurada"],
                [RefreshCw, "Borrar Historial", "Eliminar todos los registros"],
                [ExternalLink, "Sitio Web Oficial", "pixelbait.app"],
              ].map(([Icon, title, sub], i, arr) => (
                <div key={title as string}>
                  <div
                    style={{
                      display: "flex",
                      alignItems: "center",
                      gap: 10,
                      padding: "11px 14px",
                      cursor: "pointer",
                    }}
                  >
                    <div
                      style={{
                        width: 30,
                        height: 30,
                        borderRadius: 9,
                        background: "#111827",
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                      }}
                    >
                      <Icon size={14} color={VIOLET} />
                    </div>
                    <div style={{ flex: 1 }}>
                      <p style={{ fontSize: 11, fontWeight: 600, color: "#f0f2f8" }}>{title as string}</p>
                      <p style={{ fontSize: 8.5, color: "#555" }}>{sub as string}</p>
                    </div>
                    <ChevronRight size={13} color="#3a3a5a" />
                  </div>
                  {i < arr.length - 1 && (
                    <div style={{ height: 1, background: "rgba(107,47,160,0.1)", marginLeft: 54 }} />
                  )}
                </div>
              ))}
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

// ── Root App ─────────────────────────────────────────────────────────────────
export default function App() {
  return (
    <div
      style={{
        minHeight: "100vh",
        background: "#000",
        fontFamily: "'Inter', sans-serif",
      }}
    >
      {/* Hero header */}
      <div
        style={{
          textAlign: "center",
          padding: "48px 24px 32px",
          background: "radial-gradient(ellipse 80% 40% at 50% 0%, rgba(107,47,160,0.2) 0%, transparent 70%)",
          borderBottom: "1px solid rgba(107,47,160,0.15)",
        }}
      >
        <div style={{ display: "flex", alignItems: "center", justifyContent: "center", gap: 10, marginBottom: 12 }}>
          <div
            style={{
              width: 44,
              height: 44,
              borderRadius: 14,
              background: `linear-gradient(135deg, ${VIOLET}, #3d1570)`,
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              boxShadow: `0 0 30px ${VIOLET}55`,
            }}
          >
            <Shield size={22} color="#fff" />
          </div>
          <div style={{ textAlign: "left" }}>
            <h1
              style={{
                fontSize: 28,
                fontWeight: 900,
                letterSpacing: 2,
                color: "#f0f2f8",
                lineHeight: 1,
                marginBottom: 2,
              }}
            >
              PIXEL BAIT
            </h1>
            <p style={{ fontSize: 10, color: "#6B2FA0", fontWeight: 700, letterSpacing: 3, textTransform: "uppercase" }}>
              Cybersecurity · Android App
            </p>
          </div>
        </div>
        <p style={{ fontSize: 13, color: "#6b7899", maxWidth: 500, margin: "0 auto" }}>
          Mockup de alta fidelidad — 4 pantallas principales del flujo de usuario
        </p>

        {/* Color palette chips */}
        <div style={{ display: "flex", alignItems: "center", justifyContent: "center", gap: 8, marginTop: 16 }}>
          {[["#000000", "True Black"], ["#1B2A4A", "Primary Blue"], ["#6B2FA0", "Accent Violet"]].map(([color, name]) => (
            <div key={color} style={{ display: "flex", alignItems: "center", gap: 5 }}>
              <div
                style={{
                  width: 14,
                  height: 14,
                  borderRadius: 4,
                  background: color,
                  border: `1px solid rgba(255,255,255,0.15)`,
                }}
              />
              <span style={{ fontSize: 9, color: "#555", fontFamily: "'JetBrains Mono', monospace" }}>{name}</span>
            </div>
          ))}
        </div>
      </div>

      {/* Phone grid */}
      <div
        style={{
          display: "flex",
          flexWrap: "wrap",
          justifyContent: "center",
          gap: 32,
          padding: "40px 24px 60px",
          maxWidth: 1400,
          margin: "0 auto",
        }}
      >
        <PhoneFrame label="RF-01/02/03 — Onboarding">
          <Screen1Onboarding />
        </PhoneFrame>

        <PhoneFrame label="RF-04/05/06 — Escáner QR">
          <Screen2Scanner />
        </PhoneFrame>

        <PhoneFrame label="RF-07/08/09/10/11 — Navegador">
          <Screen3Browser />
        </PhoneFrame>

        <PhoneFrame label="RF-12/13/14 — Historial & Ajustes">
          <Screen4History />
        </PhoneFrame>
      </div>

      {/* Footer */}
      <div
        style={{
          textAlign: "center",
          padding: "20px",
          borderTop: "1px solid rgba(107,47,160,0.1)",
          color: "#3a3a5a",
          fontSize: 10,
          fontFamily: "'JetBrains Mono', monospace",
        }}
      >
        Pixel Bait · UI/UX Mockup · Inter + JetBrains Mono · Todos los textos en Español
      </div>
    </div>
  );
}
