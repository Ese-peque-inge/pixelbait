import type { Metadata } from 'next'
import { SiteHeader } from '@/components/site-header'
import { SiteFooter } from '@/components/site-footer'

export const metadata: Metadata = {
  title: 'Política de privacidad — Pixel Bait',
  description:
    'Política de privacidad de Pixel Bait: qué datos analiza la app, cómo protegemos tu información y tus derechos.',
}

const SECTIONS = [
  {
    title: '1. Introducción',
    body: [
      'En Pixel Bait tu privacidad es una prioridad. Esta política explica qué información maneja la aplicación, con qué propósito y cómo la protegemos. Al instalar y utilizar Pixel Bait aceptas las prácticas descritas en este documento.',
    ],
  },
  {
    title: '2. Información que analizamos',
    body: [
      'Pixel Bait analiza el contenido de los códigos QR y las URLs que decides escanear o introducir con el único fin de evaluar si representan una amenaza de seguridad (phishing, malware o sitios inseguros).',
      'El análisis se realiza de forma orientada a la seguridad. No solicitamos ni requerimos que crees una cuenta, y la app no recopila datos personales identificables como tu nombre, correo o contactos.',
    ],
  },
  {
    title: '3. Permisos del dispositivo',
    body: [
      'La app solicita acceso a la cámara exclusivamente para escanear códigos QR. Este acceso se utiliza únicamente en el momento del escaneo y no para grabar ni almacenar imágenes con otros fines.',
    ],
  },
  {
    title: '4. Almacenamiento de datos',
    body: [
      'El historial de escaneos, cuando está disponible, se almacena localmente en tu dispositivo para tu comodidad. Puedes borrarlo en cualquier momento desde los ajustes de la aplicación.',
    ],
  },
  {
    title: '5. Compartición con terceros',
    body: [
      'No vendemos ni compartimos tu información personal con terceros con fines publicitarios. Cualquier verificación de seguridad de una URL se realiza para protegerte y no con fines comerciales.',
    ],
  },
  {
    title: '6. Seguridad',
    body: [
      'Aplicamos medidas técnicas razonables para proteger la información gestionada por la aplicación. Sin embargo, ningún sistema es completamente infalible, por lo que te recomendamos mantener tu dispositivo y la app actualizados.',
    ],
  },
  {
    title: '7. Cambios en esta política',
    body: [
      'Podemos actualizar esta política ocasionalmente para reflejar mejoras o cambios legales. Publicaremos cualquier cambio en esta misma página con su fecha de actualización.',
    ],
  },
  {
    title: '8. Contacto',
    body: [
      'Si tienes preguntas sobre esta política de privacidad, puedes escribirnos a hola@pixelbait.app.',
    ],
  },
]

export default function PrivacidadPage() {
  return (
    <>
      <SiteHeader />
      <main className="bg-background">
        <section className="bg-gradient-navy py-16 text-white sm:py-20">
          <div className="mx-auto max-w-3xl px-4 sm:px-6">
            <h1 className="text-balance font-display text-4xl font-bold tracking-tight sm:text-5xl">
              Política de privacidad
            </h1>
            <p className="mt-4 text-white/70">
              Última actualización: 1 de julio de 2026
            </p>
          </div>
        </section>

        <div className="mx-auto max-w-3xl px-4 py-16 sm:px-6">
          <div className="space-y-10">
            {SECTIONS.map((section) => (
              <section key={section.title}>
                <h2 className="font-display text-xl font-semibold text-secondary">
                  {section.title}
                </h2>
                {section.body.map((p, i) => (
                  <p key={i} className="mt-3 leading-relaxed text-muted-foreground">
                    {p}
                  </p>
                ))}
              </section>
            ))}
          </div>
        </div>
      </main>
      <SiteFooter />
    </>
  )
}
