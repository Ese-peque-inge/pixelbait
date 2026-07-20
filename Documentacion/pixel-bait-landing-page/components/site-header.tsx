'use client'

import { useState } from 'react'
import Link from 'next/link'
import { Menu, X } from 'lucide-react'
import { Logo } from '@/components/logo'
import { GooglePlayBadge } from '@/components/google-play-badge'

const NAV = [
  { href: '#inicio', label: 'Inicio' },
  { href: '#quienes-somos', label: 'Quiénes somos' },
  { href: '#como-funciona', label: 'Cómo funciona' },
  { href: '/privacidad', label: 'Privacidad' },
]

export function SiteHeader() {
  const [open, setOpen] = useState(false)

  return (
    <header className="sticky top-0 z-50 border-b border-border/70 bg-background/85 backdrop-blur-md">
      <div className="mx-auto flex h-16 max-w-6xl items-center justify-between gap-4 px-4 sm:px-6">
        <Link href="#inicio" aria-label="Pixel Bait, ir al inicio">
          <Logo />
        </Link>

        <nav
          aria-label="Navegación principal"
          className="hidden items-center gap-8 md:flex"
        >
          {NAV.map((item) => (
            <Link
              key={item.href}
              href={item.href}
              className="text-sm font-medium text-muted-foreground transition-colors hover:text-foreground"
            >
              {item.label}
            </Link>
          ))}
        </nav>

        <div className="hidden md:block">
          <GooglePlayBadge />
        </div>

        <button
          type="button"
          onClick={() => setOpen((v) => !v)}
          className="inline-flex h-10 w-10 items-center justify-center rounded-lg border border-border text-foreground md:hidden"
          aria-label={open ? 'Cerrar menú' : 'Abrir menú'}
          aria-expanded={open}
        >
          {open ? <X className="h-5 w-5" /> : <Menu className="h-5 w-5" />}
        </button>
      </div>

      {open && (
        <div className="border-t border-border bg-background md:hidden">
          <nav
            aria-label="Navegación móvil"
            className="mx-auto flex max-w-6xl flex-col gap-1 px-4 py-4 sm:px-6"
          >
            {NAV.map((item) => (
              <Link
                key={item.href}
                href={item.href}
                onClick={() => setOpen(false)}
                className="rounded-lg px-3 py-2.5 text-base font-medium text-foreground transition-colors hover:bg-muted"
              >
                {item.label}
              </Link>
            ))}
            <div className="mt-2 px-1">
              <GooglePlayBadge className="w-full justify-center" />
            </div>
          </nav>
        </div>
      )}
    </header>
  )
}
