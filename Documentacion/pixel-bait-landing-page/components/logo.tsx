import Image from 'next/image'
import { cn } from '@/lib/utils'

export function Logo({
  className,
  showIsotype = true,
  dark = false,
}: {
  className?: string
  showIsotype?: boolean
  dark?: boolean
}) {
  return (
    <span className={cn('inline-flex items-center gap-2', className)}>
      {showIsotype && (
        <Image
          src="/isotype.png"
          alt=""
          width={32}
          height={32}
          className="h-8 w-8 object-contain"
          aria-hidden="true"
        />
      )}
      <span className="font-display text-xl font-bold tracking-tight">
        <span className={dark ? 'text-white' : 'text-gradient-brand'}>PIXEL</span>
        <span className="text-gradient-purple">BAIT</span>
      </span>
    </span>
  )
}
