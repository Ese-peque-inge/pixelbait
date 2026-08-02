import Link from 'next/link'
import { Mail, Globe, MessageCircle } from 'lucide-react'
import { Logo } from '@/components/logo'

export function SiteFooter() {
  return (
    <footer className="bg-ink text-white">
      <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6">
        <div className="flex flex-col justify-between gap-10 md:flex-row">
          <div className="max-w-sm">
            <Logo dark />
            <p className="mt-4 text-sm leading-relaxed text-white/60">
              Ciberseguridad simple para tu día a día. Detecta el anzuelo antes
              de morder.
            </p>
          </div>

          <div className="grid grid-cols-2 gap-10 sm:grid-cols-3">
            <div>
              <h3 className="font-display text-sm font-semibold">Producto</h3>
              <ul className="mt-4 space-y-2 text-sm text-white/60">
                <li><Link href="#como-funciona" className="hover:text-white">Cómo funciona</Link></li>
                <li><Link href="#descarga" className="hover:text-white">Descargar</Link></li>
                <li><Link href="#quienes-somos" className="hover:text-white">Quiénes somos</Link></li>
              </ul>
            </div>
            <div>
              <h3 className="font-display text-sm font-semibold">Legal</h3>
              <ul className="mt-4 space-y-2 text-sm text-white/60">
                <li><Link href="/privacidad" className="hover:text-white">Política de privacidad</Link></li>
                <li><a href="mailto:hola@pixelbait.app" className="hover:text-white">Contacto</a></li>
              </ul>
            </div>
            <div>
              <h3 className="font-display text-sm font-semibold">Síguenos</h3>
              <div className="mt-4 flex gap-3">
                {[
                  { icon: MessageCircle, label: 'Comunidad' },
                  { icon: Globe, label: 'Sitio web' },
                  { icon: Mail, label: 'Correo' },
                ].map((s) => (
                  <a
                    key={s.label}
                    href="#"
                    aria-label={s.label}
                    className="inline-flex h-9 w-9 items-center justify-center rounded-lg border border-white/15 text-white/70 transition-colors hover:bg-white/10 hover:text-white"
                  >
                    <s.icon className="h-4 w-4" aria-hidden="true" />
                  </a>
                ))}
              </div>
            </div>
          </div>
        </div>

        <div className="mt-12 flex flex-col items-center justify-between gap-4 border-t border-white/10 pt-6 text-sm text-white/50 sm:flex-row">
          <p>&copy; {new Date().getFullYear()} Pixel Bait. Todos los derechos reservados.</p>
          <p>Hecho para proteger a las personas.</p>
        </div>
      </div>
    </footer>
  )
}
