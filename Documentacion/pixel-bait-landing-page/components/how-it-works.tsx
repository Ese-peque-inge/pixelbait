import Image from 'next/image'
import { Reveal } from '@/components/reveal'

const STEPS = [
  {
    n: '01',
    src: '/app-onboarding.png',
    title: 'Configura y comienza',
    text: 'Instala Pixel Bait, concede el permiso de cámara y estarás listo para escanear en segundos.',
  },
  {
    n: '02',
    src: '/app-qr-scan.png',
    title: 'Escanea y analiza',
    text: 'Apunta a un código QR o pega una URL. Pixel Bait evalúa el destino y clasifica el nivel de riesgo al instante.',
  },
  {
    n: '03',
    src: '/app-block.png',
    title: 'Navega protegido',
    text: 'Si detecta phishing o malware, la app bloquea el acceso y te mantiene a salvo antes de abrir el enlace.',
  },
]

export function HowItWorks() {
  return (
    <section id="como-funciona" className="bg-background py-20 sm:py-24">
      <div className="mx-auto max-w-6xl px-4 sm:px-6">
        <Reveal className="mx-auto max-w-2xl text-center">
          <h2 className="text-balance font-display text-3xl font-bold tracking-tight sm:text-4xl">
            Cómo funciona la app
          </h2>
          <p className="mt-4 text-pretty text-muted-foreground">
            Protección en tres pasos simples, desde el primer escaneo hasta el
            bloqueo de amenazas.
          </p>
        </Reveal>

        <div className="mt-14 grid gap-10 sm:grid-cols-2 lg:grid-cols-3">
          {STEPS.map((step, i) => (
            <Reveal key={step.n} delay={i * 120} className="flex flex-col items-center text-center">
              <div className="relative">
                <div
                  aria-hidden="true"
                  className="absolute inset-0 -z-10 translate-y-6 scale-90 rounded-full bg-gradient-purple opacity-20 blur-2xl"
                />
                <Image
                  src={step.src || '/placeholder.svg'}
                  alt={`Pixel Bait paso ${step.n}: ${step.title}`}
                  width={220}
                  height={460}
                  className="w-[200px] rounded-[1.75rem] shadow-xl"
                />
              </div>
              <span className="mt-6 font-display text-sm font-bold text-primary">
                Paso {step.n}
              </span>
              <h3 className="mt-1 font-display text-xl font-semibold text-secondary">
                {step.title}
              </h3>
              <p className="mt-2 max-w-xs text-sm leading-relaxed text-muted-foreground">
                {step.text}
              </p>
            </Reveal>
          ))}
        </div>
      </div>
    </section>
  )
}
