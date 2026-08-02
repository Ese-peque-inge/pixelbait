import { Lightbulb, Sparkles, Code2, ShieldCheck } from 'lucide-react'
import { Reveal } from '@/components/reveal'

const STEPS = [
  {
    icon: Lightbulb,
    title: 'Investigación y diseño',
    text: 'Estudiamos los patrones de phishing más frecuentes en códigos QR y definimos una interfaz clara que comunica el riesgo de un vistazo.',
  },
  {
    icon: Sparkles,
    title: 'Apoyo de Inteligencia Artificial',
    text: 'Usamos IA como parte activa del proceso: para acelerar el diseño de pantallas, generar y revisar código, y afinar la lógica de detección y clasificación de amenazas.',
  },
  {
    icon: Code2,
    title: 'Desarrollo de la app',
    text: 'Construimos la aplicación Android priorizando el rendimiento, el bajo consumo y una experiencia fluida al escanear y analizar enlaces.',
  },
  {
    icon: ShieldCheck,
    title: 'Pruebas y validación',
    text: 'Validamos la detección con casos reales de sitios maliciosos y seguros, ajustando los niveles de riesgo para reducir falsos positivos.',
  },
]

export function Development() {
  return (
    <section className="relative overflow-hidden bg-ink py-20 text-white sm:py-24">
      <div
        aria-hidden="true"
        className="pointer-events-none absolute left-1/2 top-0 h-72 w-72 -translate-x-1/2 rounded-full bg-gradient-purple opacity-20 blur-3xl"
      />
      <div className="relative mx-auto max-w-6xl px-4 sm:px-6">
        <Reveal className="mx-auto max-w-2xl text-center">
          <span className="inline-flex items-center gap-2 rounded-full border border-white/15 bg-white/5 px-3 py-1 text-xs font-medium text-white/80">
            <Sparkles className="h-3.5 w-3.5 text-purple-light" />
            Cómo se construyó
          </span>
          <h2 className="mt-4 text-balance font-display text-3xl font-bold tracking-tight sm:text-4xl">
            Diseñada con propósito, potenciada con IA
          </h2>
          <p className="mt-4 text-pretty text-white/70">
            La Inteligencia Artificial fue una pieza clave del proceso de
            construcción, desde el diseño hasta la generación de código y el
            análisis de amenazas.
          </p>
        </Reveal>

        <ol className="mt-14 grid gap-6 sm:grid-cols-2 lg:grid-cols-4">
          {STEPS.map((step, i) => (
            <Reveal key={step.title} delay={i * 100}>
              <li className="relative h-full rounded-2xl border border-white/10 bg-ink-card p-6">
                <div className="mb-4 flex items-center gap-3">
                  <span className="inline-flex h-10 w-10 items-center justify-center rounded-lg bg-gradient-purple">
                    <step.icon className="h-5 w-5 text-white" aria-hidden="true" />
                  </span>
                  <span className="font-display text-sm font-bold text-purple-light">
                    0{i + 1}
                  </span>
                </div>
                <h3 className="font-display text-lg font-semibold">
                  {step.title}
                </h3>
                <p className="mt-2 text-sm leading-relaxed text-white/65">
                  {step.text}
                </p>
              </li>
            </Reveal>
          ))}
        </ol>
      </div>
    </section>
  )
}
