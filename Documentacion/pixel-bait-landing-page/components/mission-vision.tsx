import { Target, Compass } from 'lucide-react'
import { Reveal } from '@/components/reveal'

const ITEMS = [
  {
    icon: Target,
    title: 'Misión',
    text: 'Proteger a las personas frente a las amenazas ocultas en códigos QR y enlaces, ofreciendo una herramienta de análisis clara, rápida y accesible que anticipe el engaño antes de que ocurra.',
  },
  {
    icon: Compass,
    title: 'Visión',
    text: 'Convertirnos en el estándar de confianza para el escaneo seguro en dispositivos móviles, ampliando la protección a nuevos vectores de fraude digital a medida que evolucionan las amenazas.',
  },
]

export function MissionVision() {
  return (
    <section className="bg-background py-20 sm:py-24">
      <div className="mx-auto max-w-6xl px-4 sm:px-6">
        <Reveal className="mx-auto max-w-2xl text-center">
          <h2 className="text-balance font-display text-3xl font-bold tracking-tight sm:text-4xl">
            Por qué existe Pixel Bait
          </h2>
          <p className="mt-4 text-pretty text-muted-foreground">
            Creemos que la seguridad digital debe ser simple de entender y estar
            al alcance de todos.
          </p>
        </Reveal>

        <div className="mt-12 grid gap-6 md:grid-cols-2">
          {ITEMS.map((item, i) => (
            <Reveal key={item.title} delay={i * 120}>
              <article className="h-full rounded-2xl border border-border bg-card p-8 transition-shadow hover:shadow-lg">
                <span className="inline-flex h-12 w-12 items-center justify-center rounded-xl bg-accent text-accent-foreground">
                  <item.icon className="h-6 w-6" aria-hidden="true" />
                </span>
                <h3 className="mt-5 font-display text-2xl font-bold text-secondary">
                  {item.title}
                </h3>
                <p className="mt-3 leading-relaxed text-muted-foreground">
                  {item.text}
                </p>
              </article>
            </Reveal>
          ))}
        </div>
      </div>
    </section>
  )
}
