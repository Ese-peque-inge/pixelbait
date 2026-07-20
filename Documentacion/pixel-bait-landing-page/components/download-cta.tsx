import Image from 'next/image'
import { GooglePlayBadge } from '@/components/google-play-badge'
import { Reveal } from '@/components/reveal'

export function DownloadCta() {
  return (
    <section id="descarga" className="bg-muted/40 py-20 sm:py-24">
      <div className="mx-auto max-w-5xl px-4 sm:px-6">
        <Reveal>
          <div className="relative overflow-hidden rounded-3xl bg-gradient-navy px-6 py-14 text-center text-white sm:px-12">
            <div
              aria-hidden="true"
              className="pointer-events-none absolute -right-16 -top-16 h-64 w-64 rounded-full bg-gradient-purple opacity-40 blur-3xl"
            />
            <Image
              src="/isotype.png"
              alt=""
              aria-hidden="true"
              width={120}
              height={120}
              className="relative mx-auto mb-6 w-24"
            />
            <h2 className="relative text-balance font-display text-3xl font-bold tracking-tight sm:text-4xl">
              Empieza a escanear con confianza
            </h2>
            <p className="relative mx-auto mt-4 max-w-xl text-pretty text-white/75">
              Descarga Pixel Bait gratis y protege cada QR y enlace que abras en
              tu dispositivo Android.
            </p>
            <div className="relative mt-8 flex justify-center">
              <GooglePlayBadge size="lg" />
            </div>
          </div>
        </Reveal>
      </div>
    </section>
  )
}
