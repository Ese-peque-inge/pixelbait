import { Analytics } from '@vercel/analytics/next'
import type { Metadata, Viewport } from 'next'
import { Inter, Space_Grotesk } from 'next/font/google'
import './globals.css'

const inter = Inter({
  subsets: ['latin'],
  variable: '--font-inter',
  display: 'swap',
})

const spaceGrotesk = Space_Grotesk({
  subsets: ['latin'],
  variable: '--font-space-grotesk',
  display: 'swap',
})

export const metadata: Metadata = {
  title: 'Pixel Bait — Detecta el anzuelo antes de morder',
  description:
    'Pixel Bait es la app de ciberseguridad para Android que analiza códigos QR y URLs en busca de phishing, malware y sitios inseguros. Descárgala en Google Play.',
  generator: 'v0.app',
  keywords: [
    'Pixel Bait',
    'ciberseguridad',
    'escáner QR',
    'phishing',
    'malware',
    'Android',
    'seguridad móvil',
  ],
  openGraph: {
    title: 'Pixel Bait — Detecta el anzuelo antes de morder',
    description:
      'Analiza códigos QR y URLs en busca de amenazas. Protégete del phishing con Pixel Bait.',
    type: 'website',
  },
}

export const viewport: Viewport = {
  themeColor: '#0b0e14',
  colorScheme: 'light',
}

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <html
      lang="es"
      className={`${inter.variable} ${spaceGrotesk.variable} bg-background`}
    >
      <body className="font-sans antialiased">
        {children}
        {process.env.NODE_ENV === 'production' && <Analytics />}
      </body>
    </html>
  )
}
