'use client'

import { useEffect, useState } from 'react'
import Image from 'next/image'
import { ShieldCheck, ScanLine, Ban } from 'lucide-react'
import { GooglePlayBadge } from '@/components/google-play-badge'

const SCREENS = [
  { src: '/app-onboarding.png', label: 'Onboarding' },
  { src: '/app-qr-scan.png', label: 'Escáner QR' },
  { src: '/app-block.png', label: 'Bloqueo de sitio' },
]

export function Hero() {
  const [active, setActive] = useState(0)
  const [offset, setOffset] = useState(0)

  useEffect(() => {
    const id = setInterval(() => {
      setActive((v) => (v + 1) % SCREENS.length)
    }, 3500)
    return () => clearInterval(id)
  }, [])

  useEffect(() => {
    const onScroll = () => setOffset(window.scrollY)
    window.addEventListener('scroll', onScroll, { passive: true })
    return () => window.removeEventListener('scroll', onScroll)
  }, [])

  return (
    <section
      id="inicio"
      className="relative overflow-hidden bg-ink text-white"
    >
      {/* decorative glow + watermark */}
      <div
        aria-hidden="true"
        className="pointer-events-none absolute -left-40 top-0 h-96 w-96 rounded-full bg-gradient-purple opacity-25 blur-3xl"
      />
      <div
        aria-hidden="true"
        className="pointer-events-none absolute right-0 top-40 h-80 w-80 rounded-full bg-navy-light opacity-40 blur-3xl"
      />
      <Image
        src="/isotype.png"
        alt=""
        aria-hidden="true"
        width={520}
        height={520}
        className="pointer-events-none absolute -right-24 bottom-0 hidden w-[520px] opacity-10 lg:block"
      />

      <div className="relative mx-auto grid max-w-6xl items-center gap-12 px-4 py-20 sm:px-6 lg:grid-cols-2 lg:py-28">
        <div className="max-w-xl">
          <span className="inline-flex items-center gap-2 rounded-full border border-white/15 bg-white/5 px-3 py-1 text-xs font-medium text-white/80">
            <ShieldCheck className="h-3.5 w-3.5 text-purple-light" />
            Seguridad para Android
          </span>
          <h1 className="mt-5 text-balance font-display text-4xl font-bold leading-tight tracking-tight sm:text-5xl lg:text-6xl">
            Detecta el anzuelo <span className="text-gradient-purple">antes de morder</span>
          </h1>
          <p className="mt-6 text-pretty text-lg leading-relaxed text-white/70">
            Pixel Bait analiza códigos QR y enlaces en tiempo real para detectar
            phishing, malware y sitios inseguros. Escanea con confianza y navega
            protegido.
          </p>

          <div className="mt-8 flex flex-col gap-4 sm:flex-row sm:items-center">
            <GooglePlayBadge size="lg" />
            <a
              href="#como-funciona"
              className="inline-flex items-center justify-center rounded-xl border border-white/20 px-5 py-4 text-sm font-semibold text-white transition-colors hover:bg-white/10"
            >
              Ver cómo funciona
            </a>
          </div>

          <dl className="mt-10 grid grid-cols-3 gap-4 border-t border-white/10 pt-6 text-center sm:text-left">
            {[
              { icon: ScanLine, k: 'Análisis', v: 'de QR y URLs' },
              { icon: Ban, k: 'Bloqueo', v: 'de phishing' },
              { icon: ShieldCheck, k: 'Alertas', v: 'en tiempo real' },
            ].map((s) => (
              <div key={s.k} className="flex flex-col items-center sm:items-start">
                <s.icon className="mb-2 h-5 w-5 text-purple-light" aria-hidden="true" />
                <dt className="font-display text-sm font-semibold">{s.k}</dt>
                <dd className="text-xs text-white/60">{s.v}</dd>
              </div>
            ))}
          </dl>
        </div>

        {/* 3D phone mockup */}
        <div className="flex justify-center lg:justify-end">
          <div
            className="relative"
            style={{ perspective: '1400px' }}
          >
            <div
              aria-hidden="true"
              className="absolute inset-0 -z-10 translate-y-8 scale-90 rounded-[3rem] bg-gradient-purple opacity-40 blur-3xl"
            />
            <div
              className="animate-float"
              style={{ transform: `translateY(${offset * -0.03}px)` }}
            >
              <div
                className="relative aspect-[9/19] w-[260px] sm:w-[300px]"
                style={{
                  transform: 'rotateY(-16deg) rotateX(4deg)',
                  transformStyle: 'preserve-3d',
                }}
              >
                {SCREENS.map((screen, i) => (
                  <Image
                    key={screen.src}
                    src={screen.src || '/placeholder.svg'}
                    alt={`Pantalla de la app Pixel Bait: ${screen.label}`}
                    fill
                    priority={i === 0}
                    sizes="300px"
                    className={`rounded-[2rem] object-cover shadow-2xl transition-opacity duration-1000 ${
                      active === i ? 'opacity-100' : 'opacity-0'
                    }`}
                  />
                ))}
              </div>
            </div>

            {/* floating chips */}
            <div className="absolute -left-6 top-16 hidden animate-float rounded-xl border border-white/10 bg-ink-card/90 px-3 py-2 text-xs font-medium text-white shadow-lg backdrop-blur sm:flex sm:items-center sm:gap-2" style={{ animationDelay: '1s' }}>
              <span className="h-2 w-2 rounded-full bg-danger" /> Amenaza bloqueada
            </div>
            <div className="absolute -right-4 bottom-24 hidden animate-float rounded-xl border border-white/10 bg-ink-card/90 px-3 py-2 text-xs font-medium text-white shadow-lg backdrop-blur sm:flex sm:items-center sm:gap-2" style={{ animationDelay: '2s' }}>
              <span className="h-2 w-2 rounded-full bg-safe" /> Enlace seguro
            </div>
          </div>
        </div>
      </div>
    </section>
  )
}
