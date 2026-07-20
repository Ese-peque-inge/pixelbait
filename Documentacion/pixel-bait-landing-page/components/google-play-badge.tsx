import { cn } from '@/lib/utils'

export const GOOGLE_PLAY_URL =
  'https://play.google.com/store/apps/details?id=com.pixelbait.app'

export function GooglePlayBadge({
  className,
  size = 'md',
}: {
  className?: string
  size?: 'md' | 'lg'
}) {
  const large = size === 'lg'
  return (
    <a
      href={GOOGLE_PLAY_URL}
      target="_blank"
      rel="noopener noreferrer"
      aria-label="Descargar Pixel Bait en Google Play"
      className={cn(
        'group inline-flex items-center gap-3 rounded-xl bg-navy px-5 text-white transition-transform duration-300 hover:-translate-y-0.5 hover:shadow-lg',
        large ? 'py-4' : 'py-3',
        className,
      )}
    >
      <svg
        viewBox="0 0 512 512"
        className={cn(large ? 'h-8 w-8' : 'h-7 w-7', 'shrink-0')}
        aria-hidden="true"
      >
        <path
          fill="#00d1ff"
          d="M47 24C42 27 39 33 39 42v428c0 9 3 15 8 18l238-232L47 24z"
        />
        <path
          fill="#00e676"
          d="M47 24l238 232 66-64L96 14C78 4 60 12 47 24z"
        />
        <path
          fill="#ff3d00"
          d="M285 256l66-64 88 50c22 13 22 41 0 54l-88 50-66-90z"
        />
        <path
          fill="#ffc400"
          d="M47 488c13 12 31 20 49 10l255-140-66-64L47 488z"
        />
      </svg>
      <span className="flex flex-col leading-tight">
        <span className="text-[10px] uppercase tracking-wide opacity-80">
          Disponible en
        </span>
        <span className={cn('font-display font-semibold', large ? 'text-lg' : 'text-base')}>
          Google Play
        </span>
      </span>
    </a>
  )
}
