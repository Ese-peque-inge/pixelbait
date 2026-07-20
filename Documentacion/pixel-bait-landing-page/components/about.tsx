import Image from 'next/image'
import { Users } from 'lucide-react'
import { Reveal } from '@/components/reveal'

export function About() {
  return (
    <section id="quienes-somos" className="bg-muted/40 py-20 sm:py-24">
      <div className="mx-auto grid max-w-6xl items-center gap-12 px-4 sm:px-6 lg:grid-cols-2">
        <Reveal>
          <span className="inline-flex items-center gap-2 rounded-full bg-accent px-3 py-1 text-xs font-medium text-accent-foreground">
            <Users className="h-3.5 w-3.5" />
            Quiénes somos
          </span>
          <h2 className="mt-4 text-balance font-display text-3xl font-bold tracking-tight sm:text-4xl">
            Un equipo enfocado en la seguridad cotidiana
          </h2>
          <p className="mt-5 leading-relaxed text-muted-foreground">
            Pixel Bait nace como un proyecto que une diseño, desarrollo y
            ciberseguridad con un propósito claro: dar a las personas una defensa
            simple frente a los engaños digitales más comunes. Los códigos QR se
            han vuelto parte del día a día —menús, pagos, accesos— y también una
            puerta de entrada para el fraude.
          </p>
          <p className="mt-4 leading-relaxed text-muted-foreground">
            Somos un equipo pequeño y multidisciplinario que combina experiencia
            en desarrollo móvil, análisis de amenazas y experiencia de usuario
            para construir una app confiable, ligera y fácil de usar por
            cualquier persona.
          </p>
        </Reveal>

        <Reveal delay={120}>
          <div className="relative overflow-hidden rounded-2xl border border-border bg-gradient-navy p-8 text-white">
            <Image
              src="/isotype.png"
              alt=""
              aria-hidden="true"
              width={180}
              height={180}
              className="pointer-events-none absolute -right-6 -top-6 w-44 opacity-20"
            />
            <div className="relative grid grid-cols-2 gap-6">
              {[
                { k: 'Enfoque', v: 'Privacidad primero' },
                { k: 'Plataforma', v: 'Android' },
                { k: 'Análisis', v: 'QR y URLs' },
                { k: 'Objetivo', v: 'Cero víctimas de phishing' },
              ].map((s) => (
                <div key={s.k} className="rounded-xl border border-white/10 bg-white/5 p-4">
                  <p className="text-xs uppercase tracking-wide text-white/60">
                    {s.k}
                  </p>
                  <p className="mt-1 font-display text-lg font-semibold">
                    {s.v}
                  </p>
                </div>
              ))}
            </div>
          </div>
        </Reveal>
      </div>
    </section>
  )
}
